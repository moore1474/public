package com.m1474.tfidf.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Utility class that wraps a map. Used to simplify counting terms in documents.
 *
 */
public class Counter {

	private Map<String, Integer> map = new HashMap<>();

	/**
	 * Increments the count of a term. Sets to 1 if term hasn't been encountered
	 * before.
	 * 
	 * @param term
	 *            Term to be incremented
	 */
	public void increment(String term) {
		if (!map.containsKey(term)) {
			map.put(term, 0);
		}
		map.put(term, getCount(term) + 1);
	}

	/**
	 * Returns the count of the given term.
	 * @param term
	 * @return The count of the term
	 */
	public int getCount(String term) {
		if (!map.containsKey(term)) {
			return -1;
		}
		return map.get(term);
	}

	/**
	 * 
	 * Returns all the terms found thus far.
	 */
	public Set<String> getterms() {
		return map.keySet();
	}
}