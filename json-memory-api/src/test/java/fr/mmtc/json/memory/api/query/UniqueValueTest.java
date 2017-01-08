/*
 * json-memory-api
 * ===============
 * 
 * UniqueValueTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
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
 * UniqueValue unit test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class UniqueValueTest {
    
    public UniqueValueTest() {
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
     * Test of getFieldPathName method, of class UniqueValue.
     */
    @Test
    public void testGetFieldPathName() {
        UniqueValue instance = new UniqueValue();
        assertNull(instance.getFieldPathName());
        instance.setFieldPathName("TestValue");
        assertNotNull(instance.getFieldPathName());
        assertEquals("TestValue", instance.getFieldPathName());
    }
}
