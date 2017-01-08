/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLDocumentsTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mmtc.UnitTest;
import fr.mmtc.json.memory.api.query.FilterQuery;
import fr.mmtc.json.memory.api.query.SearchQuery;
import fr.mmtc.json.memory.api.query.UniqueValue;
import java.io.IOException;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * NoSQLDocuments Unit TTest
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLDocumentsTest {
    
    private NoSQLDocuments noSQLDocuments;
    
    public NoSQLDocumentsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            noSQLDocuments = new NoSQLDocuments("db");
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            noSQLDocuments.addDocuments(mapper.readTree("{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}"));
            noSQLDocuments.addDocuments(mapper.readTree("{\"model\": {\"marque\": \"Renault\",\"modèle\": \"Clio\"}}"));
        } catch (IOException ex) {
            assertNull(ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class NoSQLDocuments.
     */
    @Test
    public void testGetName() {
        assertEquals("db", noSQLDocuments.getName());
    }

    /**
     * Test of getDocuments method, of class NoSQLDocuments.
     */
    @Test
    public void testGetDocuments() {
        Queue<NoSQLDocument> result = noSQLDocuments.getDocuments();
        assertEquals(2, result.size());
    }

    /**
     * Test of clear method, of class NoSQLDocuments.
     */
    @Test
    public void testClear() {
        assertEquals(2, noSQLDocuments.getNbrDocuments());
        noSQLDocuments.clear();
        assertEquals(0, noSQLDocuments.getNbrDocuments());
    }

    /**
     * Test of addDocuments method, of class NoSQLDocuments.
     */
    @Test
    public void testAddDocuments() {
        try {
            NoSQLDocuments instance = new NoSQLDocuments("db");
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            instance.addDocuments(mapper.readTree("{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}"));
            Queue<NoSQLDocument> result = instance.getDocuments();
            assertEquals(1, result.size());
            assertEquals("Peugeot", result.peek().getValue("model.marque"));
            instance.addDocuments(mapper.readTree("{\"model\": {\"marque\": \"Renault\",\"modèle\": \"Clio\"}}"));
            result = instance.getDocuments();
            assertEquals(2, result.size());
            assertEquals("Peugeot", result.poll().getValue("model.marque"));
            assertEquals("Renault", result.poll().getValue("model.marque"));
        } catch (IOException ex) {
            assertNull(ex);
        }
    }

    /**
     * Test of getNbrDocuments method, of class NoSQLDocuments.
     */
    @Test
    public void testGetNbrDocuments() {
        int result = noSQLDocuments.getNbrDocuments();
        assertEquals(2, result);
    }

    /**
     * Test of searchByUniqueValue method, of class NoSQLDocuments.
     */
    @Test
    public void testSearchByUniqueValue() {
        UniqueValue uniqueValue = new UniqueValue();
        uniqueValue.setFieldPathName("model.marque");
        SearchQuery query = new SearchQuery();
        query.setRepositoryName("db");
        query.setUniqueValue(uniqueValue);
        Set<String> result = noSQLDocuments.searchByUniqueValue(query);
        Assert.assertArrayEquals(new String[] {"Renault", "Peugeot"}, ((HashSet)result).toArray());
    }

    /**
     * Test of searchByFilterQuery method, of class NoSQLDocuments.
     */
    @Test
    public void testSearchByFilterQuery() {
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.setFieldPathName("model.marque");
        filterQuery.setValue("Dacia");
        SearchQuery query = new SearchQuery();
        query.setRepositoryName("db");
        query.setFilterQuery(filterQuery);
        Set<NoSQLDocument> result = noSQLDocuments.searchByFilterQuery(query);
        assertNull(result);
        filterQuery.setValue("Peugeot");
        result = noSQLDocuments.searchByFilterQuery(query);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Peugeot", result.iterator().next().getValue("model.marque"));
        filterQuery.setValue("Renault");
        result = noSQLDocuments.searchByFilterQuery(query);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Renault", result.iterator().next().getValue("model.marque"));
    }
    
}
