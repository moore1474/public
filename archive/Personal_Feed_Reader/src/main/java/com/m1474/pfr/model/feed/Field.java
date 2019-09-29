package com.m1474.pfr.model.feed;

import java.time.Duration;

import com.google.gson.annotations.SerializedName;
import com.m1474.pfr.model.Link;

public enum Field {

	@SerializedName(value = "Title")
	TITLE, @SerializedName(value = "URL")
	URL, @SerializedName(value = "Contains")
	NUM_OF_COMMENTS, @SerializedName(value = "Source")
	SOURCE, @SerializedName(value = "Source URL")
	SOURCE_URL, @SerializedName(value = "Author")
	AUTHOR, @SerializedName(value = "Group")
	GROUP, @SerializedName(value = "Type")
	TYPE, @SerializedName(value = "Age")
	AGE, @SerializedName(value = "Page")
	PAGE;

	public Object getObjValue(Link link) {
		switch (this) {
		case TITLE:
			return link.getTitle();
		case URL:
			return link.getUrl();
		case NUM_OF_COMMENTS:
			return new Integer(link.getNumOfComments());
		case SOURCE:
			return link.getSource();
		case SOURCE_URL:
			return link.getSourceUrl();
		case AUTHOR:
			return link.getAuthor();
		case GROUP:
			return link.getGrouping().toString();
		case TYPE:
			return link.getType().toString();
		case AGE:
			return new Long(System.currentTimeMillis() - link.getPosted().getTime());
		}
		return null;
	}
	
	public Object getObjValue(String value) {
		try {
		switch (this) {
		case NUM_OF_COMMENTS:
			return Integer.parseInt(value);
		case AGE:
			int expectedNum = Integer.parseInt(value.split(" ")[0]);
			String noCase = value.toLowerCase();
			long multiple = noCase.contains("second")?Duration.ofSeconds(1).toMillis():
				noCase.contains("minute")?Duration.ofMinutes(1).toMillis():
				noCase.contains("hour")?Duration.ofHours(1).toMillis():
				noCase.contains("day")?Duration.ofDays(1).toMillis():
				noCase.contains("week")?Duration.ofDays(7).toMillis():
				noCase.contains("month")?Duration.ofDays(30).toMillis():
				noCase.contains("year")?Duration.ofDays(365).toMillis():-1;
			if(multiple<0){
				throw new Exception("Invalid Time Given");
			}
			return multiple * expectedNum;
		default:
			return value;
		}
		} catch (Exception e){
			e.printStackTrace();
		}
		return new Object();
	}
}
