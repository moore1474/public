package com.m1474.pfr.z.links;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.m1474.pfr.managers.LinkManager;
import static com.m1474.pfr.managers.Managers.*;
import com.m1474.pfr.model.Link;
import com.m1474.pfr.model.feed.Feed;

@WebListener
public class RetrieveLinksJob implements ServletContextListener {

	private ExecutorService executor;
	private long timeBetweenRuns = Duration.ofMinutes(30).toMillis();
	private LinkRetrieverInterface[] linkRetrievers = new LinkRetrieverInterface[]{
			new RedditLinkRetriever(),
			new HackerNewsLinkRetriever(),
			new RomeFeedLinkRetriever()
	};
		
	public void loop() throws Exception{
		addLinksFromFeed();
		Thread.sleep(timeBetweenRuns);
		FEED_RULE_MANAGER.applyDeleteRules();
	}
	
	public void addLinksFromFeed(){
		Collection<Feed> feeds = FEED_MANAGER.getAll(false).values();
		for(Feed feed : feeds){
			for(LinkRetrieverInterface lr : linkRetrievers){
				if(lr.useForFeed(feed)){
					List<Link> links = FEED_RULE_MANAGER.removeDoNotAdds(
							feed.getFeedRules(), lr.getLinks(feed));
					links.stream().forEach(l -> l.setFromFeed(feed.getName()));
					links = applyRulesBeforeAdding(links, feed);
					LinkManager.addLinks(links.toArray(new Link[links.size()]));
					break;
				}
			}
		}
	}
	
	public List<Link> applyRulesBeforeAdding(List<Link> links, Feed feed){
		links = LINK_MANAGER.removeNonNew(links);
		FEED_RULE_MANAGER.removeNotEnoughComments(links, feed);
		FEED_RULE_MANAGER.applyBaseRules(links);
		FEED_RULE_MANAGER.applyRules(feed.getFeedRules(), links);
		return links;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		executor = Executors.newSingleThreadExecutor();
		System.setProperty("http.agent", "/u/moore1474");
		executor.submit(new Runnable() {
			@Override
			public void run() {
				while (true) {					
					try {
						loop();
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		executor.shutdown();
	}

}