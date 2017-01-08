/*
 * json-memory-api
 * ===============
 * 
 * ResponseSuccessTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.response;

import fr.mmtc.UnitTest;
import fr.mmtc.json.memory.api.response.ResponseSuccess.TypeSuccessResponse;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * ResponseSuccess unit test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class ResponseSuccessTest {
    
    public ResponseSuccessTest() {
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
     * Test of getResultStatus method, of class ResponseSuccess.
     */
    @Test
    public void testGetResultStatus() {
        ResponseSuccess instance = new ResponseSuccess();
        assertEquals(ResponseType.SUCCESS, instance.getResultStatus());
    }

    /**
     * Test of getTypeResponse method, of class ResponseSuccess.
     */
    @Test
    public void testGetTypeResponse() {
        ResponseSuccess instance = new ResponseSuccess(TypeSuccessResponse.ADDNEWDOCUMENT);
        assertEquals(TypeSuccessResponse.ADDNEWDOCUMENT, instance.getTypeResponse());
    }

    /**
     * Test of getResult method, of class ResponseSuccess.
     */
    @Test
    public void testGetResult() {
        ResponseSuccess instance = new ResponseSuccess(TypeSuccessResponse.ADDNEWDOCUMENTS, new ArrayList());
        assertEquals(ResponseType.SUCCESS, instance.getResultStatus());
        assertTrue(instance.getResult() instanceof ArrayList);
        assertEquals(TypeSuccessResponse.ADDNEWDOCUMENTS, instance.getTypeResponse());
    }
    
}
