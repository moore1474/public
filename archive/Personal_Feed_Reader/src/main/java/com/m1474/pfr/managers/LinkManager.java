package com.m1474.pfr.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m1474.pfr.model.Link;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.lang.reflect.Type;

import org.bson.Document;
import org.bson.conversions.Bson;

public class LinkManager {
	
	private static MongoCollection<Document> LINKS_TABLE;
	private static Gson gson = new Gson();
	
	private static MongoClient mongo;
	static {
		mongo = new MongoClient( "localhost" , 27017 );
		MongoDatabase db = mongo.getDatabase("pfr");
		LINKS_TABLE = db.getCollection("links"); 
	}
	
	public static String getLinkJson(String url){
		BasicDBObject query = new BasicDBObject();
		query.put("url", url);
		FindIterable<Document> iter = LINKS_TABLE.find(query);
		if(iter == null || iter.first()==null){
			return null;
		}
		return iter.first().toJson();
	}
	
	public static Link getLink(String url){
		return fromJson(getLinkJson(url));
	}
	
	public static int addLinks(Link... links) {
		int numAdded = 0;
		for (Link l : links) {
			if (getLinkJson(l.getUrl()) == null) {
				LINKS_TABLE.insertOne(Document.parse(toJson(l)));
				numAdded++;
			} else {			
				Link oldLink = getLink(l.getUrl());			
				oldLink.setLastModified(new Date());
				oldLink.setNumOfComments(l.getNumOfComments());
				updateLinks(oldLink);
			}
		}
		return numAdded;
	}

	public static void updateLinks(Link... linksWithNewInformation) {
		for (Link l : linksWithNewInformation) {
			LINKS_TABLE.findOneAndReplace(Filters.eq("url", l.getUrl()), Document.parse(toJson(l)));
		}
	}

	public static String getAllLinksJson() {
		Map<String, Document> links = new HashMap<>();
		FindIterable<Document> iter = LINKS_TABLE.find(Filters.not(Filters.eq("grouping", "PERMANENTLY_DELETED")));
		for(Document doc : iter){
			links.put(doc.getString("url"), doc);
		}
		return toJson(links);
	}
	
	public static Map<String, Link> getAllLinks(){
		Map<String, Link> links = new HashMap<>();
		FindIterable<Document> iter = LINKS_TABLE.find(Filters.not(Filters.eq("grouping", "PERMANENTLY_DELETED")));
		for(Document doc : iter){
			links.put(doc.getString("url"), fromJson(doc.toJson()));
		}
		return links;
	}
	
	public List<Link> removeNonNew(List<Link> links){
		Iterator<Link> linksIter = links.iterator();
		Map<String, Link> allLinks = this.getAllLinks();
		while(linksIter.hasNext()){
			if(allLinks.containsKey(linksIter.next().getUrl())){
				linksIter.remove();
			}
		}
		return links;
	}
	
	public List<Link> getLinksFromSource(String source){
		List<Link> links = new ArrayList<Link>();
		Bson filter = Filters.not(Filters.eq("grouping", "PERMANENTLY_DELETED"));
		if(!source.equals("BASE")){
			filter = Filters.and(filter, Filters.eq("fromFeed", source));
		}

		FindIterable<Document> iter = LINKS_TABLE.find(filter);
		for(Document doc : iter){
			links.add(fromJson(doc.toJson()));
		}
		
		
		return links;
	}
	
	private static Type linksMapType = new TypeToken<Map<String, Link>>() {
	}.getType();
	public static Map<String, Link> fromJsonList(String links) {
		return gson.fromJson(links, linksMapType);
	}

	public static Link fromJson(String link) {
		return gson.fromJson(link, Link.class);
	}

	public static String toJson(Link link) {
		return gson.toJson(link);
	}

	public static String toJson(Map<String, Document> links) {
		return gson.toJson(links);
	}
}