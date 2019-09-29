package com.m1474.pfr.z.links;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.jdom2.Element;

import com.m1474.pfr.model.Grouping;
import com.m1474.pfr.model.Link;
import com.m1474.pfr.model.LinkType;
import com.m1474.pfr.model.feed.Feed;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class RomeFeedLinkRetriever implements LinkRetrieverInterface {

	public List<Link> getLinks(Feed aFeed) {
		List<Link> links = new ArrayList<Link>();
		try {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(new URL(aFeed.getFeedUrl())));
			for (SyndEntry entry : feed.getEntries()) {
				links.add(convertLink(entry, aFeed));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return links;
	}

	private Link convertLink(SyndEntry entry, Feed rssFeed) {
		Link l = new Link();
		try {
			l.setAdded(new Date());
			l.setGrouping(Grouping.CURRENT);
			l.setLastModified(new Date());
			l.setLinkedUrl(entry.getLink());
			l.setPosted(entry.getPublishedDate());
			l.setPriority(rssFeed.getPriority());
			l.setSafeForWorkLevel(rssFeed.getSfwLevel());
			l.setSource(rssFeed.getName());
			l.setSourceUrl(rssFeed.getSourceURL());
			l.setTitle(entry.getTitle());
			l.setType(LinkType.TEXT);
			l.setUrl(entry.getComments()!=null?entry.getComments():entry.getLink());
			l.setNumOfComments(getNumOfComments(entry));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}

	private int getNumOfComments(SyndEntry entry) {
		Optional<Element> comments = entry.getForeignMarkup().stream().filter(fm -> fm.getName().equals("comments"))
				.findFirst();
		if (comments.isPresent()) {
			try {
				String commNumStr = comments.get().getContent().get(0).getValue();
				return Integer.parseInt(commNumStr);
			} catch (Exception e) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	@Override
	public boolean useForFeed(Feed feed) {
		return true;
	}

}
