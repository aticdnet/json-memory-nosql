/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLResourcesTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql.web;

import fr.mmtc.UnitTest;
import fr.mmtc.json.memory.api.query.SearchQuery;
import fr.mmtc.json.memory.api.query.UniqueValue;
import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseError;
import fr.mmtc.json.memory.api.response.ResponseSuccess;
import fr.mmtc.json.memory.manager.NoSQLManager;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * NoSQLResources Unit TTest
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLResourcesTest {
    
    public NoSQLResourcesTest() {
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
     * Test of addDocument method, of class NoSQLResources.
     */
    @Test
    public void testAddDocument() throws Exception {
        NoSQLResources instance = new NoSQLResources(new NoSQLManager());
        IResponse result = instance.addDocument("cars", "{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}");
        Assert.assertTrue(result instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)result;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.ADDNEWDOCUMENT, responseSuccess.getTypeResponse());
        
        result = instance.addDocument("cars", "{model: {\"marque\": \"Peugeot\",\"modèle\": \"308\"");
        Assert.assertTrue(result instanceof ResponseError);
        ResponseError responseError = (ResponseError)result;
        Assert.assertNotNull(responseError.getMessage());
    }

    /**
     * Test of getUnique method, of class NoSQLResources.
     */
    @Test
    public void testSearchQuery() throws Exception {
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setRepositoryName("cars");
        searchQuery.setUniqueValue(new UniqueValue());
        searchQuery.getUniqueValue().setFieldPathName("model.marque");
        NoSQLResources instance = new NoSQLResources(new NoSQLManager());
        instance.addDocument("cars", "{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}");
        IResponse result = instance.searchQuery(searchQuery);
        Assert.assertTrue(result instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)result;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNotNull(responseSuccess.getResult());
        Assert.assertArrayEquals(new String[] {"Peugeot"}, ((HashSet)responseSuccess.getResult()).toArray());
    }
    
}
