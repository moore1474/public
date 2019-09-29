package com.m1474.pfr.model.feed;

import java.util.ArrayList;
import java.util.List;

import com.m1474.pfr.model.BaseModel;
import com.m1474.pfr.model.LinkType;

public class Feed extends BaseModel {

	private String name;
	private String feedUrl;
	private String sourceURL;
	private float priority;
	private int sfwLevel;
	private int minComments;
	private LinkType type;
	private List<FeedRule> feedRules = new ArrayList<FeedRule>(); 
	
	
	
	public Feed(String name, String feedUrl, String sourceURL, float priority, int sfwLevel, int minComments,
	                                                       		LinkType type, List<FeedRule> feedRules) {
		super();
		this.name = name;
		this.feedUrl = feedUrl;
		this.sourceURL = sourceURL;
		this.priority = priority;
		this.sfwLevel = sfwLevel;
		this.minComments = minComments;
		this.type = type;
		this.feedRules = feedRules;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFeedUrl() {
		return feedUrl;
	}
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}
	public String getSourceURL() {
		return sourceURL;
	}
	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}
	public float getPriority() {
		return priority;
	}
	public void setPriority(float priority) {
		this.priority = priority;
	}
	public int getSfwLevel() {
		return sfwLevel;
	}
	public void setSfwLevel(int sfwLevel) {
		this.sfwLevel = sfwLevel;
	}
	public int getMinComments() {
		return minComments;
	}
	public void setMinComments(int minComments) {
		this.minComments = minComments;
	}
	public List<FeedRule> getFeedRules() {
		return feedRules;
	}
	public void setFeedRules(List<FeedRule> feedRules) {
		this.feedRules = feedRules;
	}
	public LinkType getType() {
		return type;
	}
	public void setType(LinkType type) {
		this.type = type;
	}
	

}