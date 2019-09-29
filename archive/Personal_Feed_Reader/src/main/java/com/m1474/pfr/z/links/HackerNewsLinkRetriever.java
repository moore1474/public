package com.m1474.pfr.z.links;

import static com.m1474.pfr.managers.Managers.FEED_RULE_MANAGER;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.m1474.pfr.model.Grouping;
import com.m1474.pfr.model.Link;
import com.m1474.pfr.model.LinkType;
import com.m1474.pfr.model.feed.Feed;
import com.m1474.pfr.z.links.extras.model.HackerNewsLink;

public class HackerNewsLinkRetriever implements LinkRetrieverInterface {

	private final static String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
	private final static String ITEM_URL = "https://hacker-news.firebaseio.com/v0/item/$$$.json?print=pretty";
	private final static String HN_ITEM_URL = "https://news.ycombinator.com/item?id=$$$";
	private static Gson gson = new Gson();

	public List<Link> getLinks(Feed feed) {
		List<Link> toReturn = new ArrayList<>();

		// Get list of top stories
		String idArray = InternetLinkReader.readTextFromUrl(TOP_STORIES_URL);
		long[] ids = gson.fromJson(idArray, long[].class);
		int pages = FEED_RULE_MANAGER.getStopOnPage(feed.getFeedRules());
		// Add top 60 items
		int ctr = 0;
		for (long id : ids) {
			String itemUrl = ITEM_URL.replace("$$$", "" + id);
			String itemJson = InternetLinkReader.readTextFromUrl(itemUrl);
			HackerNewsLink hnl = gson.fromJson(itemJson, HackerNewsLink.class);
			Link toAdd = convertLink(hnl, feed);
			if(toAdd!=null && hnl.getDescendants()>10){
				toReturn.add(toAdd);
			}
			ctr++;
			if(ctr>pages*30){
				break;
			}
		}
		return toReturn;
	}

	private Link convertLink(HackerNewsLink hnl, Feed feed) {
		try {
			Link l = new Link();
			l.setUrl(HN_ITEM_URL.replace("$$$", hnl.getId() + ""));
			if(hnl.getUrl()==null){
				l.setLinkedUrl(l.getUrl());
			} else {
				l.setLinkedUrl(hnl.getUrl());
			}
			l.setAdded(new Date());
			l.setPosted(new Date(hnl.getTime()));
			l.setGrouping(Grouping.CURRENT);
			l.setAuthor(hnl.getBy());
			l.setNumOfComments(hnl.getDescendants());
			l.setPriority(feed.getPriority());
			l.setSafeForWorkLevel(feed.getSfwLevel());
			l.setSource("Hacker News");
			l.setSourceUrl("https://news.ycombinator.com/news");
			l.setTitle(hnl.getTitle());
			l.setType(feed.getType());
			return l;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean useForFeed(Feed feed) {
		return feed.getFeedUrl().contains("https://news.ycombinator.com/");
	}

}