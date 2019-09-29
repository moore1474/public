package com.m1474.tfidf.test;

import static com.m1474.tfidf.model.TermFrequencyWeight.BINARY;
import static com.m1474.tfidf.model.TermFrequencyWeight.LOG_NORMALIZATION;
import static com.m1474.tfidf.model.TermFrequencyWeight.RAW_FREQUENCY;
import static com.m1474.tfidf.test.TestDocs.DECLARATION_TEXT;
import static com.m1474.tfidf.test.TestDocs.DECLARATION_TEXT_DOC;
import static com.m1474.tfidf.test.TestDocs.GETTYSBURG_TEXT;
import static com.m1474.tfidf.test.TestDocs.GETTYSBURG_TEXT_DOC;
import static com.m1474.tfidf.test.TestDocs.PREAMBLE_TEXT;
import static com.m1474.tfidf.test.TestDocs.PREAMBLE_TEXT_DOC;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.m1474.tfidf.TermFrequencyProcessor;
import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.TermFrequencyWeight;

public class TermFrequencyProcesserTest {

	@BeforeClass
	public static void header() {
		System.out
				.println("*********** TESTING TERM FREQUENCY PROCESSOR*******************");
	}

	@AfterClass
	public static void footer() {
		System.out
				.println("***********************************************************************\n");
	}

	@Test
	public void binaryTest() {
		test(DECLARATION_TEXT_DOC, BINARY, "and", 1.0, "that", 1.0);
		test(PREAMBLE_TEXT_DOC, BINARY, "and", 1.0, "america", 1.0);
		test(GETTYSBURG_TEXT_DOC, BINARY, "and", 1.0, "score", 1.0);
		performanceTest(BINARY);
	}

	@Test
	public void rawFrequencyTest() {
		test(DECLARATION_TEXT_DOC, RAW_FREQUENCY, "and", 1.0, "that", 3.0);
		test(PREAMBLE_TEXT_DOC, RAW_FREQUENCY, "and", 3.0, "america", 1.0);
		test(GETTYSBURG_TEXT_DOC, RAW_FREQUENCY, "and", 2.0, "score", 1.0);
		performanceTest(RAW_FREQUENCY);
	}

	@Test
	public void logNormalizationTest() {
		test(DECLARATION_TEXT_DOC, LOG_NORMALIZATION, "and", .69, "that", 1.4);
		test(PREAMBLE_TEXT_DOC, LOG_NORMALIZATION, "and", 1.35, "america", .7);
		test(GETTYSBURG_TEXT_DOC, LOG_NORMALIZATION, "and", 1.1, "score", .7);
		performanceTest(LOG_NORMALIZATION);
	}
	
	public void performanceTest(TermFrequencyWeight weight){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<10000;i++){
			sb.append(DECLARATION_TEXT + " " + PREAMBLE_TEXT + " " + GETTYSBURG_TEXT);
		}
		Document doc = new Document("Performance Test", sb.toString());
		int wordCount = sb.toString().split(" ").length;
		TermFrequencyProcessor tfp = new TermFrequencyProcessor(doc,
				weight);
		long before = System.currentTimeMillis();
		tfp.call();
		long timeTaken = System.currentTimeMillis() - before;
		System.out.print(weight + " performance Word Count " + wordCount);
		System.out.println( ",  " + timeTaken + " Milliseconds\n\n");
		
	}

	public void test(Document document, TermFrequencyWeight weight,
			String term1, double expected1, String term2, double expected2) {
		TermFrequencyProcessor tfp = new TermFrequencyProcessor(document,
				weight);
		tfp.call();
		System.out.println(weight + " Frequency test " + document.getName());
		System.out.print(document.getTermFrequencies());
		System.out.println("\n");
		assertEquals(expected1, (double) document
				.getTermFrequencies().get(term1), 0.05);
		assertEquals(expected2, (double) document
				.getTermFrequencies().get(term2), 0.05);

	}

}
