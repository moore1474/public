package com.m1474.tfidf.test;

import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.DocumentSet;

public class TestDocs {

	static final String DECLARATION_TEXT = "We hold these truths to be self-evident that all men are created equal that they are endowed by their Creator with certain unalienable Rights that among these are Life Liberty and the pursuit of Happiness"
			.toLowerCase();
	static final String PREAMBLE_TEXT = "We the people of the United States in order to form a more perfect union establish justice insure domestic tranquility provide for the common defense promote the general welfare and secure the blessings of liberty to ourselves and our posterity do ordain and establish this Constitution for the United States of America"
			.toLowerCase();
	static final String GETTYSBURG_TEXT = "Four score and seven years ago our fathers brought forth on this continent a new nation conceived in Liberty and dedicated to the proposition that all men are created equal"
			.toLowerCase();

	static final Document DECLARATION_TEXT_DOC = new Document(
			"Declaration of Indepedence", DECLARATION_TEXT);
	static final Document PREAMBLE_TEXT_DOC = new Document(
			"Preamble to the Constitution", PREAMBLE_TEXT);
	static final Document GETTYSBURG_TEXT_DOC = new Document(
			"Gettysburg Address Text", GETTYSBURG_TEXT);
	
	static final DocumentSet US_DOCUMENTS = new DocumentSet();
	
	static {
		US_DOCUMENTS.setName("US Documents");
		US_DOCUMENTS.addDocument(DECLARATION_TEXT_DOC);
		US_DOCUMENTS.addDocument(PREAMBLE_TEXT_DOC);
		US_DOCUMENTS.addDocument(GETTYSBURG_TEXT_DOC);
	}
}
