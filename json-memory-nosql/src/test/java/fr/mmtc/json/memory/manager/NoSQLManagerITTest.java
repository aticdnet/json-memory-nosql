/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLManagerITTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.manager;

import fr.mmtc.IntegrationTest;
import fr.mmtc.json.memory.api.ResponseBuilder;
import fr.mmtc.json.memory.api.query.FilterQuery;
import fr.mmtc.json.memory.api.query.SearchQuery;
import fr.mmtc.json.memory.api.query.UniqueValue;
import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseSuccess;
import java.util.HashSet;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * NoSQLManager Integrated Test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(IntegrationTest.class)
public class NoSQLManagerITTest {
    
    private static NoSQLManager noSQLManager;
    
    public NoSQLManagerITTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        noSQLManager = new NoSQLManager();
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
    
    @Test
    public void testAddDb() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String db  = IOUtils.toString(classLoader.getResource("db.json").openStream(), "UTF-8");
        
        IResponse iresponse = noSQLManager.loadJSON(db);
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.ADDNEWDOCUMENTS, responseSuccess.getTypeResponse());
    }

    @Test
    public void testAdd308() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String db308  = IOUtils.toString(classLoader.getResource("308.json").openStream(), "UTF-8");
        
        IResponse iresponse = noSQLManager.loadJSON("cars", db308);
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.ADDNEWDOCUMENT, responseSuccess.getTypeResponse());
    }
    
    @Test
    public void testSearchQueryWithoutQuery() throws Exception {
        IResponse iresponse = ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(null));
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNull(responseSuccess.getResult());
    }
    
    @Test
    public void testSearchQueryWithoutRepository() throws Exception {
        IResponse iresponse = ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(new SearchQuery()));
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNull(responseSuccess.getResult());
    }
    
    @Test
    public void testSearchQueryUniqueMarque() throws Exception {
        testAdd308();
        
        SearchQuery query = new SearchQuery();
        query.setRepositoryName("cars");
        query.setUniqueValue(new UniqueValue());
        query.getUniqueValue().setFieldPathName("model.marque");
        
        IResponse iresponse = ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(query));
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNotNull(responseSuccess.getResult());
        Assert.assertArrayEquals(new String[] {"Renault", "Citroën", "Peugeot"}, ((HashSet)responseSuccess.getResult()).toArray());
        testAdd308();
        
        iresponse = ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(query));
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNotNull(responseSuccess.getResult());
        Assert.assertArrayEquals(new String[] {"Renault", "Citroën", "Peugeot"}, ((HashSet)responseSuccess.getResult()).toArray());
    }
    
    @Test
    public void testSearchQueryFilterUnknownMarque() throws Exception {
        testAdd308();
        
        SearchQuery query = new SearchQuery();
        query.setRepositoryName("cars");
        query.setFilterQuery(new FilterQuery());
        query.getFilterQuery().setFieldPathName("model.marque");
        query.getFilterQuery().setValue("Sun");
        
        IResponse iresponse = ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(query));
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNull(responseSuccess.getResult());
    }

    @Test
    public void testSearchQueryFilterMarque() throws Exception {
        noSQLManager.clear();
        testAdd308();
        
        SearchQuery query = new SearchQuery();
        query.setRepositoryName("cars");
        query.setFilterQuery(new FilterQuery());
        query.getFilterQuery().setFieldPathName("model.marque");
        query.getFilterQuery().setValue("Peugeot");
        
        IResponse iresponse = ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(query));
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNotNull(responseSuccess.getResult());
        Assert.assertEquals(1, ((HashSet)responseSuccess.getResult()).size());
        query.getFilterQuery().setValue("Citroën");
        testAddDb();
        
        iresponse = ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(query));
        
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNotNull(responseSuccess.getResult());
        Assert.assertEquals(3, ((HashSet)responseSuccess.getResult()).size());
    }
}
