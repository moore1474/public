package com.m1474.tfidf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * Function for weighting the computed term frequency of a document. 4 are
 * provided as as static fields below. See <a
 * href="https://en.wikipedia.org/wiki/Tf%E2%80%93idf">Wikipedia tf-idf</a> for
 * more information.
 */
@Entity
public class TermFrequencyWeight {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Transient
	private TfWeightFunction weightFunction;

	/**
	 * @param name Name of the weight function
	 * @param weightFunction Weight function to apply.
	 */
	public TermFrequencyWeight(String name, TfWeightFunction weightFunction) {
		super();
		this.name = name;
		this.weightFunction = weightFunction;
	}

	public TermFrequencyWeight() {

	}

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
	 * See <a
	 * href="https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency_2"
	 * >Wikipedia tf-idf Term Frequency</a>.
	 */
	public static final TermFrequencyWeight BINARY = new TermFrequencyWeight(
			"Binary",
			(double termFrequency, double maxTermFrequency) -> termFrequency > 0 ? 1
					: 0);

	/**
	 * See <a
	 * href="https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency_2"
	 * >Wikipedia tf-idf Term Frequency</a>.
	 */
	public static final TermFrequencyWeight RAW_FREQUENCY = new TermFrequencyWeight(
			"Raw Frequency",
			(double termFrequency, double maxTermFrequency) -> termFrequency);

	/**
	 * See <a
	 * href="https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency_2"
	 * >Wikipedia tf-idf Term Frequency</a>.
	 */
	public static final TermFrequencyWeight LOG_NORMALIZATION = new TermFrequencyWeight(
			"Log Normalization",
			(double termFrequency, double maxTermFrequency) -> Math
					.log(1.0 + termFrequency));

	/**
	 * See <a
	 * href="https://en.wikipedia.org/wiki/Tf%E2%80%93idf#Term_frequency_2"
	 * >Wikipedia tf-idf Term Frequency</a>.
	 */
	public static final TermFrequencyWeight DOUBLE_NORMALIZATION_POINT_FIVE = new TermFrequencyWeight(
			"Double Normalization", (double termFrequency,
					double maxTermFrequency) -> .5 + .5 * termFrequency
					/ maxTermFrequency);

	/**
	 * Name of the weighting.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name of the wieght function.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Weight function
	 */
	public TfWeightFunction getWeightFunction() {
		return weightFunction;
	}

	/**
	 * Weight function
	 */
	public void setWeightFunction(TfWeightFunction weightFunction) {
		this.weightFunction = weightFunction;
	}

	/**
	 * Function to use when computing term frequency of a document.
	 */
	@FunctionalInterface
	public interface TfWeightFunction {
		/**
		 * @param termFrequency
		 *            The raw frequency of a term
		 * @param maxTermFrequency
		 *            The maximum count of a term in a document.
		 * @return
		 */
		public double applyWeight(double termFrequency, double maxTermFrequency);
	}

	@Override
	public String toString() {
		return name;
	}
}