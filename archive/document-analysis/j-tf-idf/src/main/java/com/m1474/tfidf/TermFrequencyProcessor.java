package com.m1474.tfidf;


import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.TermFrequencyWeight;
import com.m1474.tfidf.utils.Counter;

/**
 * Computes Term frequency for a document.
 *
 */
public class TermFrequencyProcessor implements Callable<Document> {

	private Document document;
	private StringTokenizer tokenizer;
	private TermFrequencyWeight termFrequencyWeight;
	private String maxTerm;
	private Counter counter = new Counter();

	/**
	 * Uses Raw Frequency as default term frequency weight.
	 * @param document Document to calculate term frequency on.
	 * @param delimeter Delimiter to separate terms. Defaults to ' '.
	 */
	public TermFrequencyProcessor(Document document,
			String... delimeter) {
		this(document, TermFrequencyWeight.RAW_FREQUENCY, delimeter);
	}
	
	/**
	 * @param document Document to calculate term frequency on.
	 *  @param termFrequencyWeight termFrequencyWeight to use.
	 * @param delimeter Delimiter to separate terms. Defaults to ' '.
	 */
	public TermFrequencyProcessor(Document document,
			TermFrequencyWeight termFrequencyWeight, 
			String... delimeter) {
		super();
		this.document = document;

		this.termFrequencyWeight = termFrequencyWeight;
		if (delimeter == null || delimeter.length == 0)
			tokenizer = new StringTokenizer(document.getText());
		else
			tokenizer = new StringTokenizer(document.getText(), delimeter[0]);
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 * Calls the performe frequency count and returns the document.
	 */
	public Document call() {
		performFrequencyCount();
		return document;
	}


	/**
	 * Perform term frequency count.
	 * @return
	 */
	private Map<String, Double> performFrequencyCount() {
		while (tokenizer.hasMoreTokens()) {

			// Get Token and current Count
			String term = tokenizer.nextToken();
			counter.increment(term);
			if (maxTerm == null) {
				maxTerm = term;
			}

			// Tracking max term
			if (counter.getCount(term) + 1 > counter.getCount(maxTerm)) {
				maxTerm = term;
			}

		}
		document.setTermFrequencies(applyWeights());
		return document.getTermFrequencies();
	}

	private SortedMap<String, Double> applyWeights() {
		SortedMap<String, Double> toReturn = new TreeMap<>();
		// Apply Weights
		for (String term : counter.getterms()) {
			double weightApplied = termFrequencyWeight.getWeightFunction().applyWeight(counter.getCount(term), counter.getCount(maxTerm));
			toReturn.put(term, weightApplied);
		}
		return toReturn;
	}

	
	
}