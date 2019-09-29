package com.m1474.tfidf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * Function for weighting the computed term Inverse Document Frequency of a
 * document. 5 are provided as as static fields below. See <a
 * href="https://en.wikipedia.org/wiki/Tf%E2%80%93idf">Wikipedia tf-idf</a> for
 * more information.
 */
@Entity
public class InverseDocumentFrequencyWeight {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Transient
	private IdfWeightFunction weightFunction;

	/**
	 * Returns randomly generated ID, only used when linked to Database via JPA.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID, only used when linked to Database via JPA.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * See <a href=
	 * "https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency.E2.80.93Inverse_document_frequency"
	 * >Wikipedia tf-idf Inverse Document Frequency</a>.
	 */
	public static final InverseDocumentFrequencyWeight UNARY = new InverseDocumentFrequencyWeight(
			"Unary", (double numOfDocs, double numOfDocsWithTerm,
					double maxTermCountOfDocs) -> numOfDocsWithTerm > 0 ? 1 : 0);
	/**
	 * See <a href=
	 * "https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency.E2.80.93Inverse_document_frequency"
	 * >Wikipedia tf-idf Inverse Document Frequency</a>.
	 */
	public static final InverseDocumentFrequencyWeight INVERSE_FREQUENCY = new InverseDocumentFrequencyWeight(
			"Inverse Frequency", (double numOfDocs, double numOfDocsWithTerm,
					double maxTermCountOfDocs) -> Math.log(numOfDocs
					/ numOfDocsWithTerm));
	/**
	 * See <a href=
	 * "https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency.E2.80.93Inverse_document_frequency"
	 * >Wikipedia tf-idf Inverse Document Frequency</a>.
	 */
	public static final InverseDocumentFrequencyWeight INVERSE_FREQUENCY_SMOOTH = new InverseDocumentFrequencyWeight(
			"Inverse Frequency Smooth",
			(double numOfDocs, double numOfDocsWithTerm,
					double maxTermCountOfDocs) -> Math.log(1 + numOfDocs
					/ numOfDocsWithTerm));
	/**
	 * See <a href=
	 * "https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency.E2.80.93Inverse_document_frequency"
	 * >Wikipedia tf-idf Inverse Document Frequency</a>.
	 */
	public static final InverseDocumentFrequencyWeight INVERSE_FREQUENCY_MAX = new InverseDocumentFrequencyWeight(
			"Inverse Frequency Max",
			(double numOfDocs, double numOfDocsWithTerm,
					double maxTermCountOfDocs) -> Math.log(1
					+ maxTermCountOfDocs / numOfDocsWithTerm));
	/**
	 * See <a href=
	 * "https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency.E2.80.93Inverse_document_frequency"
	 * >Wikipedia tf-idf Inverse Document Frequency</a>.
	 */
	public static final InverseDocumentFrequencyWeight PROBABALISTIC_INVERSE_FREQUENCY = new InverseDocumentFrequencyWeight(
			"Probabalistic Inverse Frequency",
			(double numOfDocs, double numOfDocsWithTerm,
					double maxTermCountOfDocs) -> Math.log(1
					+ (numOfDocs - numOfDocsWithTerm) / numOfDocsWithTerm));

	/**
	 * @param name Name of the weight function.
	 * @param weightFunction Weight function to apply.
	 */
	public InverseDocumentFrequencyWeight(String name,
			IdfWeightFunction weightFunction) {
		super();
		this.name = name;
		this.weightFunction = weightFunction;
	}

	public InverseDocumentFrequencyWeight() {

	}

	/**
	 * Name of the weight function
	 */
	public String getName() {
		return name;
	}

	/**
	 * Weight function
	 */
	public IdfWeightFunction getWeightFunction() {
		return weightFunction;
	}

	/**
	 * Function to use when computing Inverse Document frequency of a document set.
	 */
	@FunctionalInterface
	public interface IdfWeightFunction {
		/**
		 * @param numOfDocs Nums of documents in the document set.
		 * @param numOfDocsWithTerm num of documents containting the given term.
		 * @param maxTermCountOfDocs Count of term with max frequency in the document set.
		 * @return
		 */
		public double applyWeight(double numOfDocs, double numOfDocsWithTerm,
				double maxTermCountOfDocs);

	}

	@Override
	public String toString() {
		return name;
	}

}