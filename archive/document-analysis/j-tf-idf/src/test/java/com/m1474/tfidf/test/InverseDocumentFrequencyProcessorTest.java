package com.m1474.tfidf.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.m1474.tfidf.InverseDocumentFrequencyProcessor;
import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.InverseDocumentFrequencyWeight;
import static com.m1474.tfidf.model.InverseDocumentFrequencyWeight.*;
import static org.junit.Assert.assertEquals;

public class InverseDocumentFrequencyProcessorTest {

	@BeforeClass
	public static void header() {
		System.out
				.println("*********** TESTING INVERSE DOCUMENT FREQUENCY PROCESSOR*******************");
	}

	@Test
	public void unaryFrequencyTest() throws Exception {
		test(UNARY, "to", 1, "men", 1, "unalienable", 1);
		performanceTest(UNARY);
	}

	@Test
	public void inverseFrequencyTest() throws Exception {
		test(INVERSE_FREQUENCY, "to", 0, "men", .4, "unalienable", 1.09);
		performanceTest(INVERSE_FREQUENCY);
	}

	@Test
	public void inverseFrequencySmoothTest() throws Exception {
		test(INVERSE_FREQUENCY_SMOOTH, "to", .69, "men", .91, "unalienable",
				1.38);
		performanceTest(INVERSE_FREQUENCY_SMOOTH);
	}

	@Test
	public void inverseFrequencyMax() throws Exception {
		test(INVERSE_FREQUENCY_MAX, "to", .69, "men", .91, "unalienable", 1.38);
		performanceTest(INVERSE_FREQUENCY_MAX);
	}

	@Test
	public void probablisticInverseFrequency() throws Exception {
		test(PROBABALISTIC_INVERSE_FREQUENCY, "to", 0, "men", .4, "unalienable", 1.09);
		performanceTest(PROBABALISTIC_INVERSE_FREQUENCY);
	}

	public void test(InverseDocumentFrequencyWeight weight, String term1,
			double expected1, String term2, double expected2, String term3,
			double expected3) throws Exception {
		InverseDocumentFrequencyProcessor proc = new InverseDocumentFrequencyProcessor(
				TestDocs.US_DOCUMENTS, weight);
		proc.call();
		Map<String, Double> ifMap = TestDocs.US_DOCUMENTS
				.getInverseDocumentFrequencies();
		System.out.println(weight + " TEST");
		System.out.println(ifMap);
		assertEquals(expected1, (double) ifMap.get(term1), 0.05);
		assertEquals(expected2, (double) ifMap.get(term2), 0.05);
		assertEquals(expected3, (double) ifMap.get(term3), 0.05);
	}

	public void performanceTest(InverseDocumentFrequencyWeight weight) throws Exception{
		Map<String, Document> originalSet = TestDocs.US_DOCUMENTS.getDocuments();
		Map<String, Document> tempInflatedSet = new HashMap<>();
		List<Document> docList = new ArrayList<>();
		docList.addAll(originalSet.values());
		for(int i=0;i<10000;i++){
			tempInflatedSet.put(i + "", docList.get(i%3));
		}
		TestDocs.US_DOCUMENTS.setDocuments(tempInflatedSet);
		InverseDocumentFrequencyProcessor proc = new InverseDocumentFrequencyProcessor(
				TestDocs.US_DOCUMENTS, weight);
		long before = System.currentTimeMillis();
		proc.call();
		long timeTaken = System.currentTimeMillis() - before;
		System.out.print("Performance Document Count 30000");
		System.out.println(",  " + timeTaken + " Milliseconds\n\n");
		TestDocs.US_DOCUMENTS.setDocuments(originalSet);
	}

	@AfterClass
	public static void footer() {
		System.out
				.println("***********************************************************************\n");
	}

}