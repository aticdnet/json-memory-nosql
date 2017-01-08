/*
 * json-memory-api
 * ===============
 * 
 * ResponseBuilderTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api;

import fr.mmtc.UnitTest;
import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseError;
import fr.mmtc.json.memory.api.response.ResponseSuccess;
import static fr.mmtc.json.memory.api.response.ResponseSuccess.TypeSuccessResponse.ADDNEWDOCUMENTS;
import fr.mmtc.json.memory.api.response.ResponseType;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * ResponseBuilder unit test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class ResponseBuilderTest {
    
    public ResponseBuilderTest() {
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
     * Test of success method, of class ResponseBuilder.
     */
    @Test
    public void testSuccess_ResponseSuccessTypeSuccessResponse() {
        IResponse result = ResponseBuilder.success(ADDNEWDOCUMENTS);
        assertEquals(ResponseType.SUCCESS, result.getResultStatus());
        assertTrue(result instanceof ResponseSuccess);
        assertEquals(ADDNEWDOCUMENTS, ((ResponseSuccess)result).getTypeResponse());
    }

    /**
     * Test of success method, of class ResponseBuilder.
     */
    @Test
    public void testSuccess_ResponseSuccessTypeSuccessResponse_Object() {
        IResponse result = ResponseBuilder.success(ADDNEWDOCUMENTS, new ArrayList());
        assertEquals(ResponseType.SUCCESS, result.getResultStatus());
        assertTrue(result instanceof ResponseSuccess);
        assertTrue(((ResponseSuccess)result).getResult() instanceof ArrayList);
        assertEquals(ADDNEWDOCUMENTS, ((ResponseSuccess)result).getTypeResponse());
    }

    /**
     * Test of error method, of class ResponseBuilder.
     */
    @Test
    public void testError() {
        IResponse result = ResponseBuilder.error(new Exception("Exception in xxx"));
        assertEquals(ResponseType.ERROR, result.getResultStatus());
        assertTrue(result instanceof ResponseError);
        assertNotNull(((ResponseError)result).getMessage());
        assertEquals("Exception in xxx", ((ResponseError)result).getMessage());
    }
    
}
