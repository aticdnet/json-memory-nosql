/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLDocumentTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mmtc.UnitTest;
import fr.mmtc.json.memory.api.query.FilterQuery;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * NoSQLDocument Unit TTest
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLDocumentTest {
    
    private NoSQLDocument noSQLDocument;
    
    public NoSQLDocumentTest() {
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
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            noSQLDocument = new NoSQLDocument(mapper.readTree("{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}"));
        } catch (IOException ex) {
            assertNull(ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getValue method, of class NoSQLDocument.
     */
    @Test
    public void testGetValue() {
        assertEquals("Peugeot", noSQLDocument.getValue("model.marque"));
        assertEquals("308", noSQLDocument.getValue("model.modèle"));
    }

    /**
     * Test of canApplyFilter method, of class NoSQLDocument.
     */
    @Test
    public void testCanApplyFilter() {
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.setFieldPathName("model.marque");
        filterQuery.setValue("Dacia");
        assertFalse(noSQLDocument.canApplyFilter(filterQuery));
        filterQuery.setValue("Renault");
        assertFalse(noSQLDocument.canApplyFilter(filterQuery));
        filterQuery.setValue("Peugeot");
        assertTrue(noSQLDocument.canApplyFilter(filterQuery));
    }
    
}
