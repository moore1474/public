package com.m1474.pfr.z.links;

import java.util.List;

import com.m1474.pfr.model.Link;
import com.m1474.pfr.model.feed.Feed;

public interface LinkRetrieverInterface {

	abstract List<Link> getLinks(Feed feed);

	public boolean useForFeed(Feed feed);
}