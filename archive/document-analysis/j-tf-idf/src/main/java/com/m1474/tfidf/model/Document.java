package com.m1474.tfidf.model;

import java.util.Map;
import java.util.TreeMap;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import org.eclipse.persistence.indirection.IndirectMap;

/**
 * Contains the text of a given document. In addition to name it may also hold
 * arbitrary attributes in the {@link #getAttribute} map. Holds the
 * TermFrequency results and tf-idf results for the given document.
 */
@Entity
public class Document {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "DOCUMENT_SET_ID", nullable = false)
	private DocumentSet documentSet;
	private String name;
	@Lob
	private String text;
	@ElementCollection
	@CollectionTable(name = "TERM_FREQUENCIES")
	@MapKeyColumn(name = "TERM")
	private IndirectMap<String, Double> termFrequencies = new IndirectMap<String, Double>(
			new TreeMap<String, Double>());
	@ElementCollection
	@CollectionTable(name = "TERM_FREQUENCY_INVERSE_DOCUMENT_FREQUENCIES")
	@MapKeyColumn(name = "TERM")
	private IndirectMap<String, Double> termFrequencyInverseDocumentFrequencies = new IndirectMap<String, Double>(
			new TreeMap<String, Double>());
	@ElementCollection
	@CollectionTable(name = "DOCUMENT_ATTRIBUTES")
	@MapKeyColumn(name = "ATTRIBUTE")
	private IndirectMap<String, String> attribute = new IndirectMap<String, String>(
			new TreeMap<String, String>());

	/**
	 * No args constructor for JPA. All documents should have a name and text.
	 */
	public Document() {

	}

	/**
	 * @param name
	 *            Name of the document
	 * @param text
	 *            Text of the document
	 */
	public Document(String name, String text) {
		super();
		this.name = name;
		this.text = text;
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
	 * Returns the name of the document.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the document.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the text of the document.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of the document.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Term frequencies of the document. Key is the term. Value is frequency in
	 * document computed using given {@link TermFrequencyWeight} in owning
	 * {@link DocumentSet}
	 */
	public IndirectMap<String, Double> getTermFrequencies() {
		return termFrequencies;
	}

	/**
	 * Term frequencies of the document. Key is the term. Value is frequency in
	 * document computed using given {@link TermFrequencyWeight} in owning
	 * {@link DocumentSet}
	 */
	public void setTermFrequencies(Map<String, Double> termFrequencies) {
		this.termFrequencies = new IndirectMap<String, Double>(termFrequencies);
	}

	/**
	 * tf-idfs of the document. Key is the term. Value is frequency in document
	 * computed using given {@link TermFrequencyWeight} and
	 * {@link InverseDocumentFrequencyWeight} in owning {@link DocumentSet}
	 */
	public IndirectMap<String, Double> getTermFrequencyInverseDocumentFrequencies() {
		return termFrequencyInverseDocumentFrequencies;
	}

	/**
	 * tf-idfs of the document. Key is the term. Value is frequency in document
	 * computed using given {@link TermFrequencyWeight} and
	 * {@link InverseDocumentFrequencyWeight} in owning {@link DocumentSet}
	 */
	public void setTermFrequencyInverseDocumentFrequencies(
			Map<String, Double> termFrequencyInverseDocumentFrequencies) {
		this.termFrequencyInverseDocumentFrequencies = new IndirectMap<String, Double>(
				termFrequencyInverseDocumentFrequencies);
	}

	/**
	 * DocumentSet this document belongs to
	 */
	public DocumentSet getDocumentSet() {
		return documentSet;
	}

	/**
	 * DocumentSet this document belongs to
	 */
	public void setDocumentSet(DocumentSet documentSet) {
		this.documentSet = documentSet;
	}

	/**
	 * Map of any other attributes in the document
	 */
	public IndirectMap<String, String> getAttribute() {
		return attribute;
	}

	/**
	 * Map of any other attributes in the document
	 */
	public void setAttribute(IndirectMap<String, String> attribute) {
		this.attribute = attribute;
	}

}