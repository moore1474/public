package com.m1474.tfidf.test;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.Test;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import com.m1474.tfidf.TermFrequencyInverseDocumentFrequencyProcessor;
import com.m1474.tfidf.model.Document;
import com.m1474.tfidf.model.DocumentSet;
import static org.junit.Assert.assertEquals;
import static com.m1474.tfidf.model.TermFrequencyWeight.*;
import static com.m1474.tfidf.model.InverseDocumentFrequencyWeight.*;

public class TestDatabaseStorage {

	@Test
	public void test() throws Exception {
		//Create entity manager
		Map<String, Object> props = getTestPersistenceProperties();
		EntityManager em = getEntityManager(props);
		
		//Populate the Document Set
		TermFrequencyInverseDocumentFrequencyProcessor proc = new TermFrequencyInverseDocumentFrequencyProcessor(
				TestDocs.US_DOCUMENTS, UNARY, BINARY);
		proc.call();
		
		//Persist
		em.getTransaction().begin();
		em.persist(TestDocs.US_DOCUMENTS);
		em.getTransaction().commit();
		em.close();
		
		
		//Validate Persistence
		em = getEntityManager(props);
		em.getTransaction().begin();
		DocumentSet ds = (DocumentSet) em.createQuery("Select ds from DocumentSet ds").getResultList().get(0);
		Document doc = ds.getDocuments().get("Declaration of Indepedence");
		assertEquals(ds.getName(), "US Documents");
		assertEquals(3, ds.getDocuments().size());
		assertEquals(77, ds.getInverseDocumentFrequencies().size());
		assertEquals(30, doc.getTermFrequencies().size());
		assertEquals(30, doc.getTermFrequencyInverseDocumentFrequencies().size());
		em.getTransaction().commit();
		em.close();
		
		org.hsqldb.DatabaseManager.closeDatabases(0);
	}
	
	public void populateDocumentSet(){
		
	}
	
	public Map<String, Object> getTestPersistenceProperties(){
		Map<String, Object> props = new HashMap<>();		
		props.put(PersistenceUnitProperties.JDBC_DRIVER, "org.hsqldb.jdbcDriver");
		props.put("javax.persistence.jdbc.url", "jdbc:hsqldb:mem:test");
		props.put("javax.persistence.jdbc.user", "sa");
		props.put("javax.persistence.jdbc.password", "");
		props.put("eclipselink.ddl-generation", "create-tables");
		props.put("eclipselink.ddl-generation.output-mode", "database");
		props.put("eclipselink.weaving", "false");
		props.put("eclipselink.logging.level", "FINE");
		return props;
	}
	
	public EntityManager getEntityManager(Map<String, Object> props){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceProviderClass(org.eclipse.persistence.jpa.PersistenceProvider.class);
		emf.setPackagesToScan("com.m1474.tfidf.model");
		emf.setPersistenceUnitName("test");
		emf.setJpaPropertyMap(props);
		emf.afterPropertiesSet();
		return emf.getObject().createEntityManager();
	}
}