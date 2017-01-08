/*
 * json-memory-api
 * ===============
 * 
 * IResponseTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
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
 * IResponse unit test
 * 
 *@author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class IResponseTest {
    
    public IResponseTest() {
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
     * Test of getResultStatus method, of class IResponse.
     */
    @Test
    public void testGetResultStatus() {
        IResponse instance = new IResponseImpl();
        
        String expResult = "TestValue";
        String result = instance.getResultStatus();
        assertEquals(expResult, result);
    }

    public class IResponseImpl implements IResponse {

        @Override
        public String getResultStatus() {
            return "TestValue";
        }
    }
    
}
