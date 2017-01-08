/*
 * json-memory-nosql
 * =================
 * 
 * CORSFilterTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory;

import fr.mmtc.UnitTest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * CORSFilter unit test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class CORSFilterTest {
    
    public CORSFilterTest() {
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
     * Test of filter method, of class CORSFilter.
     */
    @Test
    public void testFilter() throws Exception {
        ContainerRequestContext paramContainerRequestContext = Mockito.mock(ContainerRequestContext.class);
        ContainerResponseContext paramContainerResponseContext = Mockito.mock(ContainerResponseContext.class);
        MultivaluedMap<String, Object> map = new MultivaluedHashMap<String, Object>();
	Mockito.when(paramContainerResponseContext.getHeaders()).thenReturn(map);
        CORSFilter instance = new CORSFilter();
        instance.filter(paramContainerRequestContext, paramContainerResponseContext);
        assertEquals(5, paramContainerResponseContext.getHeaders().size());
        assertEquals("*", paramContainerResponseContext.getHeaders().get("Access-Control-Allow-Origin").get(0));
        assertEquals("origin, content-type, accept, authorization", paramContainerResponseContext.getHeaders().get("Access-Control-Allow-Headers").get(0));
        assertEquals("true", paramContainerResponseContext.getHeaders().get("Access-Control-Allow-Credentials").get(0));
        assertEquals("GET, POST, PUT, DELETE, OPTIONS, HEAD", paramContainerResponseContext.getHeaders().get("Access-Control-Allow-Methods").get(0));
        assertEquals("1209600", paramContainerResponseContext.getHeaders().get("Access-Control-Max-Age").get(0));
    }
    
}
