package com.m1474.tfidf.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import org.eclipse.persistence.indirection.IndirectMap;

/**
 * 
 * Root of the model. Contains a map of documents along with the corresponding
 * {@link TermFrequencyWeight} and {@link InverseDocumentFrequencyWeight} used
 * in tf-idf processing. Also holds Result of computing inverse document
 * frequency on the document set.
 * 
 */
@Entity
public class DocumentSet {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(targetEntity = Document.class, mappedBy = "documentSet", cascade = CascadeType.PERSIST)
	private Map<String, Document> documents = new HashMap<>();
	@ManyToOne(cascade = CascadeType.PERSIST)
	private TermFrequencyWeight termFrequencyWeight;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight;
	@ElementCollection
	@CollectionTable(name = "INVERSE_DOCUMENT_FREQUENCIES")
	@MapKeyColumn(name = "TERM")
	private IndirectMap<String, Double> inverseDocumentFrequencies = new IndirectMap<String, Double>(
			new TreeMap<String, Double>());

	@ElementCollection
	@CollectionTable(name = "DOCUMENT_ATTRIBUTES")
	@MapKeyColumn(name = "DOC_ATTRIBUTE")
	
	
	public void DocumentSet() {

	}

	/**
	 * @param name Name of the document set
	 */
	public void DocumentSet(String name) {
		this.name = name;
	}

	/**
	 * Returns randomly generated ID, only used when linked to Database via JPA.
	 * 
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
	 * Returns the name of the document set.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the document set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns map of documents mapping name to the given document.
	 */
	public Map<String, Document> getDocuments() {
		return documents;
	}

	/**
	 * Set the map of documents mapping the name to the given document.
	 */
	public void setDocuments(Map<String, Document> documents) {
		for (Document d : documents.values()) {
			d.setDocumentSet(this);
		}
		this.documents = documents;
	}

	/**
	 * Add a document to the document set.
	 * 
	 * @param document
	 *            Document to be added.
	 */
	public void addDocument(Document document) {
		document.setDocumentSet(this);
		documents.put(document.getName(), document);
	}

	/**
	 * Add a document to the document set.
	 * 
	 * @param name
	 *            Name of the document.
	 * @param text
	 *            Text of the document.
	 */
	public void addDocument(String name, String text) {
		Document doc = new Document(name, text);
		doc.setDocumentSet(this);
		addDocument(doc);
	}

	/**
	 * Weight used for term frequency.
	 */
	public TermFrequencyWeight getTermFrequencyWeight() {
		return termFrequencyWeight;
	}

	/**
	 * Weight used for term frequency.
	 */
	public void setTermFrequencyWeight(TermFrequencyWeight termFrequencyWeight) {
		this.termFrequencyWeight = termFrequencyWeight;
	}

	/**
	 * Weight used for Inverse Document Frequency.
	 */
	public InverseDocumentFrequencyWeight getInverseDocumentFrequencyWeight() {
		return inverseDocumentFrequencyWeight;
	}

	/**
	 * Weight used for Inverse Document Frequency.
	 */
	public void setInverseDocumentFrequencyWeight(
			InverseDocumentFrequencyWeight inverseDocumentFrequencyWeight) {
		this.inverseDocumentFrequencyWeight = inverseDocumentFrequencyWeight;
	}

	/**
	 * Inverse Document frequencies for the document set. Each key is a term.
	 * Each value is the computed count of the term throughout the document set.
	 */
	public IndirectMap<String, Double> getInverseDocumentFrequencies() {
		return inverseDocumentFrequencies;
	}

	/**
	 * Inverse Document frequencies for the document set. Each key is a term.
	 * Each value is the computed count of the term throughout the document set.
	 */
	public void setInverseDocumentFrequencies(
			Map<String, Double> inverseDocumentFrequencies) {
		this.inverseDocumentFrequencies = new IndirectMap<String, Double>(inverseDocumentFrequencies);
	}
	
}