package com.m1474.pfr.z.links;

import java.util.ArrayList;
import java.util.List;

import com.m1474.pfr.model.LinkType;
import com.m1474.pfr.model.feed.Feed;
import com.m1474.pfr.model.feed.FeedRule;

public class TestFeeds {

	public static Feed DILBERT_FEED = new Feed("Dilbert", "http://feed.dilbert.com/dilbert/blog?format=rss", "", 0, 0,
			0, LinkType.TEXT, new ArrayList<FeedRule>());

}