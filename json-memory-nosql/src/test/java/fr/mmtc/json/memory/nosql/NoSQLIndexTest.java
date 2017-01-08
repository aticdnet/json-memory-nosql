/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLIndexTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mmtc.UnitTest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * NoSQLIndex Unit TTest
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLIndexTest {
    
    private NoSQLIndex noSQLIndex;
    
    public NoSQLIndexTest() {
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
            noSQLIndex = new NoSQLIndex(new NoSQLDocuments("db"));
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            noSQLIndex.getValuesByKey("model.marque", "Peugeot");
            NoSQLDocument document = new NoSQLDocument(mapper.readTree("{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}"));
            noSQLIndex.updateIndex(document);
        } catch (IOException ex) {
            assertNull(ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateIndex method, of class NoSQLIndex.
     */
    @Test
    public void testUpdateIndex() {
        try {
            Set<NoSQLDocument> result = noSQLIndex.getValuesByKey("model.marque", "Peugeot");
            assertEquals(1, result.size());
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            NoSQLDocument document = new NoSQLDocument(mapper.readTree("{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}"));
            noSQLIndex.updateIndex(document);
            noSQLIndex.updateIndex(document);
            noSQLIndex.updateIndex(document);
            result = noSQLIndex.getValuesByKey("model.marque", "Peugeot");
            assertEquals(2, result.size());
            document = new NoSQLDocument(mapper.readTree("{\"model\": {\"marque\": \"Renault\",\"modèle\": \"Clio\"}}"));
            noSQLIndex.updateIndex(document);
            noSQLIndex.updateIndex(document);
            noSQLIndex.updateIndex(document);
            result = noSQLIndex.getValuesByKey("model.marque", "Peugeot");
            assertEquals(2, result.size());
        } catch (IOException ex) {
            assertNull(ex);
        }
    }

    /**
     * Test of clear method, of class NoSQLIndex.
     */
    @Test
    public void testClear() {
        assertNotEquals(0, noSQLIndex.getNbrIndexes());
        noSQLIndex.clear();
        assertEquals(0, noSQLIndex.getNbrIndexes());
    }
    
}
