package com.m1474.pfr.z.links;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.m1474.pfr.model.Grouping;
import com.m1474.pfr.model.Link;
import com.m1474.pfr.model.feed.Feed;
import com.m1474.pfr.z.links.extras.model.Child;
import com.m1474.pfr.z.links.extras.model.Data_;
import com.m1474.pfr.z.links.extras.model.RedditListing;
import static com.m1474.pfr.managers.Managers.*;

public class RedditLinkRetriever implements LinkRetrieverInterface{

	private static final String BASE_URL = "https://www.reddit.com/";
	private static final String BASE_URL_NO_DASH = "https://www.reddit.com";
	private static Gson gson = new Gson();
	private Date added = null;
	
	public boolean useForFeed(Feed feed){
		return feed.getFeedUrl().contains("reddit.com");
	}
	
	public List<Link> getLinks(Feed feed) {
		added = new Date();
		List<Link> links = new ArrayList<Link>();
			for(int i=0;i<FEED_RULE_MANAGER.getStopOnPage(feed.getFeedRules());i++){
				String after = "";
				try {
					String json = InternetLinkReader.readTextFromUrl(feed.getFeedUrl()  + ".json" + after);
					RedditListing listing = gson.fromJson(json, RedditListing.class);
					after = "?after=" + listing.getData().getAfter();
					links.addAll(getLinksFromListing(listing, feed));
					Thread.sleep(Duration.ofSeconds(10).toMillis());
				} catch (Exception e){
					e.printStackTrace();
				}
			}		
		return links;
	}
	
	public List<Link> getLinksFromListing(RedditListing listing, Feed feed){
		List<Link> links = new ArrayList<Link>();
		for(Child child : listing.getData().getChildren()){
			Data_ data = child.getData();
				links.add(convert(data, feed));
		}
		return links;
	}

	public Link convert(Data_ data, Feed feed){
		Link link = new Link();
		link.setTitle(data.getTitle());
		link.setUrl(BASE_URL_NO_DASH + data.getPermalink());
		link.setLinkedUrl(data.getUrl());
		if(data.getUrl().contains("i.reddituploads")){
			link.setLinkedUrl(link.getUrl());
		}
		link.setNumOfComments(data.getNumComments());
		link.setSource("/r/" + data.getSubreddit());
		link.setSourceUrl(BASE_URL + "r/" + data.getSubreddit());
		link.setAuthor(data.getAuthor());
		link.setAdded(added);
		link.setPosted(new Date((long)data.getCreated().doubleValue()));
		link.setGrouping(Grouping.CURRENT);
		link.setType(feed.getType());
		link.setPriority(feed.getPriority());
		link.setSafeForWorkLevel(data.getOver18()?1000:feed.getSfwLevel());
		return link;
	}
}
