package com.m1474.pfr.managers;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import com.google.gson.Gson;
import com.m1474.pfr.model.BaseModel;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public abstract class AbstractManager<M extends BaseModel> {

	private Gson gson = new Gson();
	private MongoCollection<Document> table;
	static MongoDatabase db;
	private String idColumn;
	private Field idColumnField;
	private static MongoClient mongo;
	
	static {
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDatabase("pfr");
	}

	private Class<M> type;

	public AbstractManager(Class<M> type, String tableName, String idColumn) {
		this.type = type;
		table = db.getCollection(tableName);
		this.idColumn = idColumn;
		try {
			idColumnField = type.getDeclaredField(idColumn);
			idColumnField.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} 
	}

	public M fromJson(String json) {
		return gson.fromJson(json, type);
	}

	public String toJson(M model) {
		return gson.toJson(model);
	}

	public String toJson(Map<String, Document> links) {
		return gson.toJson(links);
	}

	public Map<String, M> getAll() {
		Map<String, M> allObjects = new HashMap<>();
		FindIterable<Document> iter = table.find();
		for (Document doc : iter) {
			allObjects.put(doc.getString(idColumn), fromJson(doc.toJson()));
		}
		return allObjects;
	}

	public String getAllJson() {
		Map<String, Document> allObjects = new HashMap<>();
		FindIterable<Document> iter = table.find();
		for (Document doc : iter) {
			allObjects.put(doc.getString("name"), doc);
		}
		return toJson(allObjects);
	}

	public M getById(Object id) {
		return fromJson(getJsonById(id));
	}

	public String getJsonById(Object id) {
		BasicDBObject query = new BasicDBObject();
		query.put(idColumn, id);
		FindIterable<Document> iter = table.find(query);
		if (iter == null || iter.first() == null) {
			return null;
		}
		return iter.first().toJson();
	}

	public int add(@SuppressWarnings("unchecked") M... models) {
		int numAdded = 0;		
		try {
			for (M model : models) {
				Field idField = type.getDeclaredField(idColumn);
				idField.setAccessible(true);
				String idColumnValue = (String) idField.get(model);
				M old = getById(idColumnValue);
				model.setAdded(new Date());
				model.setLastModified(new Date());
				if (old == null) {
					table.insertOne(Document.parse(toJson(model)));
					numAdded++;
				} else {// TODO figure out how to abstract update code

				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numAdded;
	}

	public void update(@SuppressWarnings("unchecked") M... models) {
		try {
			for (M model : models) {
				
				String idColumnValue = (String) idColumnField
						.get(model);
				model.setLastModified(new Date());
				table.findOneAndReplace(Filters.eq(idColumnField.getName(), idColumnValue),
						Document.parse(toJson(model)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void delete(String... ids){
		try {
			for (String id : ids) {
				M model = getById(id);
				String idColumnValue = (String) idColumnField.get(model);
				model.setDateDeleted(new Date());
				table.findOneAndDelete(Filters.eq(idColumnField.getName(), idColumnValue));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}