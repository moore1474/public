package com.m1474.tfidf;

import static com.m1474.tfidf.model.TermFrequencyWeight.LOG_NORMALIZATION;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.DocumentSet;
import com.m1474.tfidf.model.InverseDocumentFrequencyWeight;
import com.m1474.tfidf.model.TermFrequencyWeight;

/**
 * Computes tf-idf frequency for a document set. Default thread count
 * of 5 is used. Unless otherwise specified,
 * {@link InverseDocumentFrequencyWeight#INVERSE_FREQUENCY} is used for IDF
 * weight weight and {@link TermFrequencyWeight#LOG_NORMALIZATION} is used for
 * TF weight. Call {@link Callable#call()} method on object to compute IDF for
 * document set. Results stored in
 * {@link Document#getTerm#termFrequencyInverseDocumentFrequencies()}
 */
public class TermFrequencyInverseDocumentFrequencyProcessor implements
		Callable<DocumentSet> {

	private DocumentSet documentSet;
	private static final int TF_PROCESSOR_DEFAULT = 5;
	private InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight;
	private TermFrequencyWeight termFrequencyWeight;
	private ExecutorService executorService;
	private String[] delimeter;
	private int threadCount;
	
	/**
	 * @param documentSet Set of documents to perform computation on.
	 */
	public TermFrequencyInverseDocumentFrequencyProcessor(
			DocumentSet documentSet){
		this(documentSet, documentSet.getInverseDocumentFrequencyWeight(),
				TF_PROCESSOR_DEFAULT, documentSet.getTermFrequencyWeight());
	}

	/**
	 * @param documentSet Set of documents to perform computation on.
	 * @param threadCount num of threads to use doing computation.
	 */
	public TermFrequencyInverseDocumentFrequencyProcessor(
			DocumentSet documentSet, int threadCount){
		this(documentSet, documentSet.getInverseDocumentFrequencyWeight(),
				threadCount, documentSet.getTermFrequencyWeight());
	}
	
	/**
	 * @param documentSet Set of documents to perform computation on.
	 * @param inverseDocumentFrequencyWeight IDF weight to use for computation.
	 */
	public TermFrequencyInverseDocumentFrequencyProcessor(
			DocumentSet documentSet,
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight) {
		this(documentSet, inverseDocumentFrequencyWeight, TF_PROCESSOR_DEFAULT,
				LOG_NORMALIZATION);
	}

	/**
	 * @param documentSet Set of documents to perform computation on.
	 * @param termFrequencyWeight
	 * @param inverseDocumentFrequencyWeight
	 */
	public TermFrequencyInverseDocumentFrequencyProcessor(
			DocumentSet documentSet, TermFrequencyWeight termFrequencyWeight,
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight) {
		this(documentSet, inverseDocumentFrequencyWeight, TF_PROCESSOR_DEFAULT,
				termFrequencyWeight);
	}

	/**
	 * @param documentSet Set of documents to perform computation on.
	 * @param inverseDocumentFrequencyWeight IDF weight to use
	 * @param termFrequencyWeight TF weight to use.
	 * @param delimeter Delimeter to use. Default is ' '.
	 */
	public TermFrequencyInverseDocumentFrequencyProcessor(
			DocumentSet documentSet,
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight,
			TermFrequencyWeight termFrequencyWeight, String... delimeter) {
		this(documentSet, inverseDocumentFrequencyWeight, TF_PROCESSOR_DEFAULT,
				termFrequencyWeight, delimeter);
	}

	/**
	 * @param documentSet Set of documents to perform computation on.
	 * @param inverseDocumentFrequencyWeight IDF weight to use
	 * @param threadCount num of threads to use doing computation.
	 * @param termFrequencyWeight TF weight to use.
	 * @param delimeter Delimeter to use. Default is ' '.
	 */
	public TermFrequencyInverseDocumentFrequencyProcessor(
			DocumentSet documentSet,
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight,
			int threadCount, TermFrequencyWeight termFrequencyWeight,
			String... delimeter) {
		super();
		this.documentSet = documentSet;
		this.inverseDocumentFrequencyWeight = inverseDocumentFrequencyWeight;
		this.termFrequencyWeight = termFrequencyWeight;
		this.delimeter = delimeter;
		this.threadCount = threadCount;
		executorService = Executors.newFixedThreadPool(threadCount);
	}

	@Override
	public DocumentSet call() throws Exception {
		// Call the IDF Processor
		InverseDocumentFrequencyProcessor idf = new InverseDocumentFrequencyProcessor(
				documentSet, termFrequencyWeight,
				inverseDocumentFrequencyWeight, threadCount, delimeter);
		DocumentSet docs = idf.call();

		// Calculate tfidf's
		for (Document doc : docs.getDocuments().values()) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					SortedMap<String, Double> tfidf = new TreeMap<>();
					for (String term : doc.getTermFrequencies().keySet()) {
						double tf = doc.getTermFrequencies().get(term);
						double idf = docs.getInverseDocumentFrequencies().get(
								term);
						tfidf.put(term, tf * idf);
					}
					doc.setTermFrequencyInverseDocumentFrequencies(tfidf);
				}
			});
		}
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.DAYS);
		return docs;
	}
}
