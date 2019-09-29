package com.m1474.pfr.managers;

import java.util.ArrayList;
import java.util.Map;

import com.m1474.pfr.model.LinkType;
import com.m1474.pfr.model.feed.Feed;
import com.m1474.pfr.model.feed.FeedRule;

public class FeedManager extends AbstractManager<Feed>{
	
	public FeedManager(){
		super(Feed.class, "feed", "name");
	}    

	public Map<String, Feed> getAll(boolean includeBase) {
		Map<String, Feed> feeds = super.getAll();
		if(!includeBase){
			feeds.remove("BASE");
		} else if(includeBase){
			if(!feeds.containsKey("BASE")){
				Feed baseFeed = new Feed("BASE", "-", "-", 0, 0, 0, LinkType.TEXT, new ArrayList<FeedRule>());				
				this.add(baseFeed);
				return getAll(true);
			}
		}
		return feeds;
	}
	
}