/*
 * json-memory-api
 * ===============
 * 
 * ResponseErrorTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.response;

import fr.mmtc.UnitTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * ResponseError unit test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class ResponseErrorTest {
    
    public ResponseErrorTest() {
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
     * Test of getResultStatus method, of class ResponseError.
     */
    @Test
    public void testGetResultStatus() {
        ResponseError instance = new ResponseError();
        assertEquals(ResponseType.ERROR, instance.getResultStatus());
        
    }

    /**
     * Test of getMessage method, of class ResponseError.
     */
    @Test
    public void testGetMessage() {
        Exception e = new Exception("TestValue");
        ResponseError instance = new ResponseError(e);
        assertEquals("TestValue", instance.getMessage());
    }
    
}
