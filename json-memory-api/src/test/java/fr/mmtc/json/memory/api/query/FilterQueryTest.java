/*
 * json-memory-api
 * ===============
 * 
 * FilterQueryTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
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
 * FilterQuery unit test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class FilterQueryTest {
    
    public FilterQueryTest() {
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
     * Test of getValue method, of class FilterQuery.
     */
    @Test
    public void testGetValue() {
        FilterQuery instance = new FilterQuery();
        assertNull(instance.getValue());
        instance.setValue("TestValue");
        assertNotNull(instance.getValue());
        assertEquals("TestValue", instance.getValue());
    }    
}
