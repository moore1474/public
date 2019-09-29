package com.m1474.pfr.model;

import java.util.Date;

public class Link extends BaseModel{
	
	private String title;
	private String url;//Unique ID
	private String linkedUrl;
	private int numOfComments=-1;//-1=Unkown
	private String source;
	private String sourceUrl;
	private String author;
	private Grouping grouping;
	private LinkType type;
	private Date posted;
	private float priority;//1 = highest
	private int safeForWorkLevel;//1=Safest
	private String notes="";
	private String fromFeed;
	
	private long timeToStayDeleted;
	
	public Link(){
		
	}
	
	public Link(String title, String url, String linkedUrl, int numOfComments, String source, String sourceUrl,
			String author, Grouping grouping, LinkType type, Date added,
			Date posted, float priority, int safeForWorkLevel, String notes, String fromFeed) {
		super();
		this.title = title;
		this.url = url;
		this.linkedUrl = linkedUrl;
		this.numOfComments = numOfComments;
		this.source = source;
		this.sourceUrl = sourceUrl;
		this.author = author;
		this.grouping = grouping;
		this.type = type;
		this.added = added;
		this.posted = posted;
		this.priority = priority;
		this.safeForWorkLevel = safeForWorkLevel;
		this.notes = notes;
		this.fromFeed = fromFeed;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLinkedUrl() {
		return linkedUrl;
	}
	public void setLinkedUrl(String linkedUrl) {
		this.linkedUrl = linkedUrl;
	}
	public int getNumOfComments() {
		return numOfComments;
	}
	public void setNumOfComments(int numOfComments) {
		this.numOfComments = numOfComments;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Grouping getGrouping() {
		return grouping;
	}
	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}
	public LinkType getType() {
		return type;
	}
	public void setType(LinkType type) {
		this.type = type;
	}
	
	public Date getPosted() {
		return posted;
	}
	public void setPosted(Date posted) {
		this.posted = posted;
	}
	public float getPriority() {
		return priority;
	}
	public void setPriority(float priority) {
		this.priority = priority;
	}
	public int getSafeForWorkLevel() {
		return safeForWorkLevel;
	}
	public void setSafeForWorkLevel(int safeForWorkLevel) {
		this.safeForWorkLevel = safeForWorkLevel;
	}
	public String getNotes() {
		return notes;
	}
	public void addNote(String note) {
		this.notes = notes + note + "\n";
	}

	public long getTimeToStayDeleted() {
		return timeToStayDeleted;
	}

	public void setTimeToStayDeleted(long timeToStayDeleted) {
		this.timeToStayDeleted = timeToStayDeleted;
	}

	public String getFromFeed() {
		return fromFeed;
	}

	public void setFromFeed(String fromFeed) {
		this.fromFeed = fromFeed;
	}	
}