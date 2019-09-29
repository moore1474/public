package com.m1474.pfr.model;

import com.google.gson.annotations.SerializedName;

public enum LinkType {
	
	@SerializedName(value = "Text", alternate = {"TEXT", "text"})
	TEXT,
	@SerializedName(value = "Image", alternate = {"IMAGE", "image"})
	IMAGE,
	@SerializedName(value = "Video", alternate = {"VIDEO", "video"})
	VIDEO,
	@SerializedName(value = "Audio", alternate = {"AUDIO", "audio"})
	AUDIO;
	
	public static LinkType getValue(String val){
		try {
			LinkType linkType = LinkType.valueOf(val.toUpperCase().replaceAll(" ", "_"));
			return linkType;
		} catch(IllegalArgumentException iae){
			return null;
		}
	}
}
