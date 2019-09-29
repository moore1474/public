package com.m1474.tfidf;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.DocumentSet;
import com.m1474.tfidf.model.InverseDocumentFrequencyWeight;
import com.m1474.tfidf.model.TermFrequencyWeight;
import com.m1474.tfidf.utils.Counter;
import static com.m1474.tfidf.model.TermFrequencyWeight.*;
import static com.m1474.tfidf.model.InverseDocumentFrequencyWeight.*;

/**
 * Computes Inverse Document frequency for a document set. Default thread count
 * of 5 is used. Unless otherwise specified,
 * {@link InverseDocumentFrequencyWeight#INVERSE_FREQUENCY} is used for IDF
 * weight weight and {@link TermFrequencyWeight#LOG_NORMALIZATION} is used for
 * TF weight. Call {@link Callable#call()} method on object to compute IDF for
 * document set. Results stored in
 * {@link DocumentSet#getInverseDocumentFrequencies()}
 */
public class InverseDocumentFrequencyProcessor implements Callable<DocumentSet> {

	private DocumentSet documentSet;
	private static final int THREAD_DEFAULT = 5;
	private ExecutorService executorService;
	private String[] delimeter;
	private Counter counter = new Counter();
	private String maxTerm;

	/**
	 * @param documentSet
	 *            Set of documents to perform computation on.
	 * @param delimeter
	 *            Delimiter to separate terms. Defaults to ' '.
	 */
	public InverseDocumentFrequencyProcessor(DocumentSet documentSet,
			String... delimeter) {
		this(documentSet, documentSet.getInverseDocumentFrequencyWeight(),
				documentSet.getTermFrequencyWeight(), delimeter);
	}

	/**
	 * @param documentSet
	 *            Set of documents to perform computation on.
	 * @param inverseDocumentFrequencyWeight
	 *            IDF weight to use.
	 * @param delimeter
	 *            Delimiter to separate terms. Defaults to ' '.
	 */
	public InverseDocumentFrequencyProcessor(DocumentSet documentSet,
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight,
			String... delimeter) {
		this(documentSet, LOG_NORMALIZATION, inverseDocumentFrequencyWeight,
				THREAD_DEFAULT);
	}

	/**
	 * @param documentSet
	 *            Set of documents to perform computation on.
	 * @param inverseDocumentFrequencyWeight
	 *            IDF weight to use.
	 * @param termFrequencyWeight
	 *            TF WEight to use.
	 * @param delimeter
	 *            Delimiter to separate terms. Defaults to ' '.
	 */
	public InverseDocumentFrequencyProcessor(DocumentSet documentSet,
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight,
			TermFrequencyWeight termFrequencyWeight, String... delimeter) {
		this(documentSet, termFrequencyWeight, inverseDocumentFrequencyWeight,
				THREAD_DEFAULT, delimeter);
	}

	/**
	 * @param documentSet
	 *            Set of documents to perform computation on.
	 * @param inverseDocumentFrequencyWeight
	 *            IDF weight to use.
	 * @param termFrequencyWeight
	 *            TF WEight to use.
	 * @param threadCount
	 *            Number of thread to use to process documents.
	 * @param delimeter
	 *            Delimiter to separate terms. Defaults to ' '.
	 */
	public InverseDocumentFrequencyProcessor(DocumentSet documentSet,
			TermFrequencyWeight termFrequencyWeight,
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight,
			int threadCount, String... delimeter) {
		super();
		this.documentSet = documentSet;
		if (inverseDocumentFrequencyWeight != null) {
			documentSet
					.setInverseDocumentFrequencyWeight(inverseDocumentFrequencyWeight);
		} else {
			documentSet
					.setInverseDocumentFrequencyWeight(InverseDocumentFrequencyWeight.INVERSE_FREQUENCY);
		}
		if (termFrequencyWeight == null) {
			documentSet.setTermFrequencyWeight(termFrequencyWeight);
		} else {
			documentSet
					.setTermFrequencyWeight(TermFrequencyWeight.LOG_NORMALIZATION);
		}
		this.delimeter = delimeter;
		executorService = Executors.newFixedThreadPool(threadCount);
	}

	@Override
	public DocumentSet call() throws Exception {
		boolean trackMax = documentSet.getInverseDocumentFrequencyWeight() == INVERSE_FREQUENCY_MAX;
		Set<FutureTask<Document>> tasks = submitTheTasks();
		processDocumentResults(tasks, trackMax);
		setInverseDocumentFrequencies();
		return documentSet;
	}

	private Set<FutureTask<Document>> submitTheTasks() {
		Set<FutureTask<Document>> termFrequencyTasks = new HashSet<>();
		for (Document document : documentSet.getDocuments().values()) {
			termFrequencyTasks.add(new FutureTask<Document>(
					new TermFrequencyProcessor(document, documentSet
							.getTermFrequencyWeight(), delimeter)));
		}
		for (FutureTask<Document> ft : termFrequencyTasks) {
			executorService.submit(ft);
		}
		return termFrequencyTasks;
	}

	private void processDocumentResults(
			Set<FutureTask<Document>> termFrequencyTasks, boolean trackMax)
			throws Exception {
		while (true) {
			FutureTask<Document> finishedTask = null;
			for (FutureTask<Document> task : termFrequencyTasks) {
				if (task.isDone()) {
					finishedTask = task;
					for (String term : task.get().getTermFrequencies().keySet()) {
						counter.increment(term);
						if (trackMax) {
							if (maxTerm == null) {
								maxTerm = term;
							}
							if (counter.getCount(term) > counter
									.getCount(maxTerm)) {
								maxTerm = term;
							}
						}
					}
					break;
				}
			}
			if (finishedTask != null) {
				termFrequencyTasks.remove(finishedTask);
				finishedTask = null;
			}
			if (termFrequencyTasks.size() == 0)
				break;
		}

	}

	private void setInverseDocumentFrequencies() {
		TreeMap<String, Double> doubleMap = new TreeMap<>();
		double numOfDocs = (double) documentSet.getDocuments().size();
		double maxTermCount = counter.getCount(maxTerm);
		for (String term : counter.getterms()) {
			int termCount = counter.getCount(term);
			double applied = documentSet.getInverseDocumentFrequencyWeight()
					.getWeightFunction()
					.applyWeight(numOfDocs, termCount, maxTermCount);
			doubleMap.put(term, applied);
		}
		documentSet.setInverseDocumentFrequencies(doubleMap);
	}

}