package com.m1474.pfr.model;

import com.google.gson.annotations.SerializedName;

public enum Grouping {

	@SerializedName(value = "Recent")
	RECENT,
	@SerializedName(value = "Current")
	CURRENT,
	@SerializedName(value = "Later")
	LATER,
	@SerializedName(value = "Much Later")
	MUCH_LATER,
	@SerializedName(value = "One Day")
	ONE_DAY,
	@SerializedName(value = "Favorite")
	FAVORITED,
	@SerializedName(value = "Software")
	SOFTWARE_RELATED,
	@SerializedName(value = "Career")
	CAREER,
	@SerializedName(value = "Study")
	STUDY,
	@SerializedName(value = "Game")
	GAMES,
	@SerializedName(value = "Deleted")
	DELETED,
	@SerializedName(value = "Permanently Deleted")
	PERMANENTLY_DELETED;

	public static Grouping getValue(String val){
		try {
			Grouping grouping = Grouping.valueOf(val.toUpperCase().replaceAll(" ", "_"));
			return grouping;
		} catch(IllegalArgumentException iae){
			return null;
		}
	}
	
}