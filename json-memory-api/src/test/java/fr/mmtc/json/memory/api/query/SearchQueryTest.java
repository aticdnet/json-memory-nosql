/*
 * json-memory-api
 * ===============
 * 
 * SearchQueryTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.query;

import fr.mmtc.UnitTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * SearchQuery unit test
 * 
 *@author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class SearchQueryTest {
    
    public SearchQueryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUniqueValue method, of class SearchQuery.
     */
    @Test
    public void testGetUniqueValue() {
        SearchQuery instance = new SearchQuery();
        assertNull(instance.getUniqueValue());
        instance.setUniqueValue(new UniqueValue());
        assertNotNull(instance.getUniqueValue());
        assertTrue(instance.getUniqueValue() instanceof UniqueValue);
    }

    /**
     * Test of getRepositoryName method, of class SearchQuery.
     */
    @Test
    public void testGetRepositoryName() {
        SearchQuery instance = new SearchQuery();
        assertNull(instance.getRepositoryName());
        instance.setRepositoryName("TestValue");
        assertNotNull(instance.getRepositoryName());
        assertEquals("TestValue", instance.getRepositoryName());
    }

    /**
     * Test of getFilterQuery method, of class SearchQuery.
     */
    @Test
    public void testGetFilterQuery() {
        SearchQuery instance = new SearchQuery();
        assertNull(instance.getFilterQuery());
        instance.setFilterQuery(new FilterQuery());
        assertNotNull(instance.getFilterQuery());
        assertTrue(instance.getFilterQuery() instanceof FilterQuery);
    }
}
