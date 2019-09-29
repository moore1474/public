package com.m1474.pfr.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import com.m1474.pfr.model.Link;
import com.m1474.pfr.model.feed.EffectOperator;
import com.m1474.pfr.model.feed.Feed;
import com.m1474.pfr.model.feed.FeedRule;
import static com.m1474.pfr.managers.Managers.*;

public class FeedRuleManager {
	
	public void removeNotEnoughComments(List<Link> links, Feed feed){
		Iterator<Link> linksIter = links.iterator();
		while(linksIter.hasNext()){
			if(linksIter.next().getNumOfComments() < feed.getMinComments()){
				linksIter.remove();
			}
		}
	}
	
	public void applyBaseRules(List<Link> links) {
		Feed baseFeed = FEED_MANAGER.getAll(true).values().stream().filter(f -> f.getName().equals("BASE")).findFirst()
				.get();
		removeDoNotAdds(baseFeed.getFeedRules(), links);
		applyRules(baseFeed.getFeedRules(), links);
	}

	public void applyRules(List<FeedRule> rules, List<Link> links) {
		List<FeedRule> standardRules = rules.stream()
				.filter(r -> !r.getEffectOperator().equals(EffectOperator.DO_NOT_ADD)
						&& !r.getEffectOperator().equals(EffectOperator.DELETE)
						&& !r.getEffectOperator().equals(EffectOperator.PERMANENTLY_DELETE))
				.collect(Collectors.toList());
		applyRulesFromFeed(standardRules, links, (fr, link) -> fr.getEffectOperator().applyEffect(link, fr));
	}

	public List<Link> removeDoNotAdds(List<FeedRule> rules, List<Link> links) {
		List<Link> toRemove = new ArrayList<Link>();
		List<FeedRule> dnaRules = rules.stream().filter(r -> r.getEffectOperator().equals(EffectOperator.DO_NOT_ADD))
				.collect(Collectors.toList());
		applyRulesFromFeed(dnaRules, links, (fr, link) -> toRemove.add(link));
		links.removeAll(toRemove);
		return links;
	}

	public void applyDeleteRules() {
		Map<String, Feed> allFeeds = FEED_MANAGER.getAll(true);
		for (Feed feed : allFeeds.values()) {
			List<Link> links = LINK_MANAGER.getLinksFromSource(feed.getName());
			List<FeedRule> deleteRules = feed.getFeedRules().stream()
					.filter(r -> r.getEffectOperator().equals(EffectOperator.DELETE)
							|| r.getEffectOperator().equals(EffectOperator.PERMANENTLY_DELETE))
					.collect(Collectors.toList());
			applyRulesFromFeed(deleteRules, links, (fr, link) -> fr.getEffectOperator().applyEffect(link, fr) );
			LINK_MANAGER.updateLinks(links.toArray(new Link[links.size()]));
		}
	}

	private void applyRulesFromFeed(List<FeedRule> feedRules, List<Link> links,
			BiConsumer<FeedRule, Link> howToApplyRule) {
		for (FeedRule fr : feedRules) {
			for (Link link : links) {
				if (shouldApplyRule(fr, link)) {
					if (null != fr.getEffectOperator()) {
						howToApplyRule.accept(fr, link);
					}
				}
			}
		}
	}

	public boolean shouldApplyRule(FeedRule feedRule, Link link) {
		if (null == feedRule.getField() || null == feedRule.getValue()) {
			return false;
		}
		Object actual = feedRule.getField().getObjValue(link);
		Object test = feedRule.getField().getObjValue(feedRule.getValue());
		return feedRule.getOperator().test(actual, test);
	}

	public int getStopOnPage(List<FeedRule> feedRules) {
		for (FeedRule fr : feedRules) {
			if (null != fr.getField() && fr.getField().equals("Page")) {
				return Integer.parseInt(fr.getChangeValue());
			}
		}
		return 1;
	}
}