package com.m1474.pfr.z.links;

import java.util.List;

import org.testng.annotations.Test;

import com.m1474.pfr.managers.LinkManager;
import com.m1474.pfr.model.Link;

public class RomeFeedLinkRetrieverTest {

	private RomeFeedLinkRetriever retreiver = new RomeFeedLinkRetriever();
	private RetrieveLinksJob retrieveLinksJob = new RetrieveLinksJob();
	
	@Test
	private void baseTest(){
		List<Link> links = retreiver.getLinks(TestFeeds.DILBERT_FEED);
		links = retrieveLinksJob.applyRulesBeforeAdding(links, TestFeeds.DILBERT_FEED);
		int numAdded = LinkManager.addLinks(links.toArray(new Link[links.size()]));
		System.out.println("A");
	}
	
}