package com.m1474.tfidf.test;

import static com.m1474.tfidf.model.InverseDocumentFrequencyWeight.INVERSE_FREQUENCY;
import static com.m1474.tfidf.model.InverseDocumentFrequencyWeight.PROBABALISTIC_INVERSE_FREQUENCY;
import static com.m1474.tfidf.model.TermFrequencyWeight.DOUBLE_NORMALIZATION_POINT_FIVE;
import static com.m1474.tfidf.model.TermFrequencyWeight.RAW_FREQUENCY;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.m1474.tfidf.TermFrequencyInverseDocumentFrequencyProcessor;
import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.InverseDocumentFrequencyWeight;
import com.m1474.tfidf.model.TermFrequencyWeight;

public class TermFrequencyInverseDocumentFrequencyProcessorTest {

	@BeforeClass
	public static void header() {
		System.out
				.println("*********** TESTING TERM FREQUENCY INVERSE DOCUMENT PROCESSOR*******************");
	}

	@AfterClass
	public static void footer() {
		System.out
				.println("***********************************************************************\n");
	}

	@Test
	public void testRawFrequencyInverseFrequency() throws Exception {
		test(RAW_FREQUENCY, INVERSE_FREQUENCY, "these", 2.19, "posterity", 1.09, "to", 0);
	}
	
	@Test
	public void testDoubleNormalizationProbabalisticInverseFrequency() throws Exception{
		test(DOUBLE_NORMALIZATION_POINT_FIVE, PROBABALISTIC_INVERSE_FREQUENCY, "equal", .27, "common", .64, "to", 0);
	}
	
	public void test(TermFrequencyWeight termFreqWeight,
			InverseDocumentFrequencyWeight invWeight, String term1,
			double expected1, String term2, double expected2, String term3, double expected3) throws Exception {
		System.out.println(termFreqWeight + " Term Frequency, " + invWeight + " Inverse Term Frequency, Test");
		TermFrequencyInverseDocumentFrequencyProcessor proc = new TermFrequencyInverseDocumentFrequencyProcessor(
				TestDocs.US_DOCUMENTS, termFreqWeight, invWeight);
		proc.call();
		testDoc(TestDocs.DECLARATION_TEXT_DOC, term1, expected1);
		testDoc(TestDocs.PREAMBLE_TEXT_DOC, term2, expected2);
		testDoc(TestDocs.GETTYSBURG_TEXT_DOC, term3, expected3);
		System.out.println();
	}
	
	public void testDoc(Document document, String term, double expected){
		System.out.println(document.getName() + "=" + document.getTermFrequencyInverseDocumentFrequencies());
		assertEquals(expected, (double) document.getTermFrequencyInverseDocumentFrequencies().get(term), 0.05);
	}

}
