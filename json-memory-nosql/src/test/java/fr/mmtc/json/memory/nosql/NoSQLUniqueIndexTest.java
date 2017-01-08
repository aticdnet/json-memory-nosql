/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLUniqueIndexTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
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
 * NoSQLUniqueIndex Unit TTest
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLUniqueIndexTest {
    
    private NoSQLUniqueIndex noSQLUniqueIndex;
    
    public NoSQLUniqueIndexTest() {
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
            noSQLUniqueIndex = new NoSQLUniqueIndex(new NoSQLDocuments("db"));
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            noSQLUniqueIndex.getUniqueValuesByKey("model.marque");
            NoSQLDocument document = new NoSQLDocument(mapper.readTree("{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}"));
            noSQLUniqueIndex.updateIndex(document);
        } catch (IOException ex) {
            assertNull(ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateIndex method, of class NoSQLUniqueIndex.
     */
    @Test
    public void testUpdateIndex() {
        try {
            Set<String> result = noSQLUniqueIndex.getUniqueValuesByKey("model.marque");
            Assert.assertArrayEquals(new String[] {"Peugeot"}, ((HashSet)result).toArray());
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            NoSQLDocument document = new NoSQLDocument(mapper.readTree("{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}"));
            noSQLUniqueIndex.updateIndex(document);
            noSQLUniqueIndex.updateIndex(document);
            noSQLUniqueIndex.updateIndex(document);
            result = noSQLUniqueIndex.getUniqueValuesByKey("model.marque");
            Assert.assertArrayEquals(new String[] {"Peugeot"}, ((HashSet)result).toArray());
            document = new NoSQLDocument(mapper.readTree("{\"model\": {\"marque\": \"Renault\",\"modèle\": \"Clio\"}}"));
            noSQLUniqueIndex.updateIndex(document);
            noSQLUniqueIndex.updateIndex(document);
            noSQLUniqueIndex.updateIndex(document);
            result = noSQLUniqueIndex.getUniqueValuesByKey("model.marque");
            Assert.assertArrayEquals(new String[] {"Renault", "Peugeot"}, ((HashSet)result).toArray());
            result = noSQLUniqueIndex.getUniqueValuesByKey("not.exist");
            Assert.assertArrayEquals(new String[] {}, ((HashSet)result).toArray());
        } catch (IOException ex) {
            assertNull(ex);
        }
    }

    /**
     * Test of clear method, of class NoSQLUniqueIndex.
     */
    @Test
    public void testClear() {
        assertNotEquals(0, noSQLUniqueIndex.getNbrIndexes());
        noSQLUniqueIndex.clear();
        assertEquals(0, noSQLUniqueIndex.getNbrIndexes());
    }
    
}
