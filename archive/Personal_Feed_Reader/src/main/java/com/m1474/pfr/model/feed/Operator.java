package com.m1474.pfr.model.feed;

import com.google.gson.annotations.SerializedName;

public enum Operator {

	@SerializedName(value = "Contains")
	CONTAINS, 
	@SerializedName(value = "Contains One Of")
	CONTAINS_ONE_OF, @SerializedName(value = "Equals")
	EQUALS, 
	@SerializedName(value = "Equals Ignore Case")
	EQUALS_IGNORE_CASE, 
	@SerializedName(value = "Greater Than")
	GREATER_THAN, 
	@SerializedName(value = "Less Than")
	LESS_THAN;

	@SuppressWarnings("unchecked")
	public boolean test(Object actual, Object testValue){
		switch(this){
		case EQUALS:
			return actual.equals(testValue);
		case LESS_THAN:
			return ((Comparable<Object>) actual).compareTo(testValue)<0;
		case GREATER_THAN:
			return ((Comparable<Object>) actual).compareTo(testValue)>0;
		case EQUALS_IGNORE_CASE:
			return ((String) actual).equalsIgnoreCase((String)testValue);
		case CONTAINS:
			return ((String) actual).toLowerCase().contains(((String)testValue).toLowerCase());
		case CONTAINS_ONE_OF:
			for(String str : ((String)actual).replaceAll(",", " ").split("[ \t]+")){
				String lcTest = ((String) testValue).toLowerCase();
				if(str.toLowerCase().contains(lcTest)){
					return true;
				}				
			}
			return false;
		default:
			return false;
		}
	}
}