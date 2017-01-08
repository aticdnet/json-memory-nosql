/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLManagerTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.manager;

import fr.mmtc.UnitTest;
import fr.mmtc.json.memory.api.query.FilterQuery;
import fr.mmtc.json.memory.api.query.SearchQuery;
import fr.mmtc.json.memory.api.query.UniqueValue;
import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseSuccess;
import fr.mmtc.json.memory.nosql.NoSQLDocument;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * NoSQLManager Unit TTest
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLManagerTest {
    
    private NoSQLManager instance;
    
    public NoSQLManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        instance = new NoSQLManager();
        instance.loadJSON("db", "{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadJSON method, of class NoSQLManager.
     */
    @Test
    public void testLoadJSON_String() throws Exception {
        IResponse result = instance.loadJSON("{\"cars\": [{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}]}");
        
        Assert.assertTrue(result instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)result;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.ADDNEWDOCUMENTS, responseSuccess.getTypeResponse());
    }

    /**
     * Test of loadJSON method, of class NoSQLManager.
     */
    @Test
    public void testLoadJSON_String_String() throws Exception {
        IResponse result = instance.loadJSON("cars", "{\"model\": {\"marque\": \"Peugeot\",\"modèle\": \"308\"}}");
        Assert.assertTrue(result instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)result;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.ADDNEWDOCUMENT, responseSuccess.getTypeResponse());
    }

    /**
     * Test of clear method, of class NoSQLManager.
     */
    @Test
    public void testClear() {
        assertNotEquals(0, instance.getNbrDocuments());
        instance.clear();
        assertEquals(0, instance.getNbrDocuments());
    }

    /**
     * Test of search method, of class NoSQLManager.
     */
    @Test
    public void testSearch() {
        UniqueValue uniqueValue = new UniqueValue();
        uniqueValue.setFieldPathName("model.marque");
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.setFieldPathName("model.marque");
        filterQuery.setValue("Peugeot");
        
        assertNull(instance.search(null));
        SearchQuery query = new SearchQuery();
        assertNull(instance.search(query));
        query.setRepositoryName("not.exist");
        assertNull(instance.search(query));
        query.setRepositoryName("db");
        assertNull(instance.search(query));
        query.setUniqueValue(uniqueValue);
        Object result = instance.search(query);
        Assert.assertNotNull(result);
        Assert.assertArrayEquals(new String[] {"Peugeot"}, ((HashSet)result).toArray());
        query.setUniqueValue(null);
        query.setFilterQuery(filterQuery);
        result = instance.search(query);
        Assert.assertNotNull(result);
        Assert.assertEquals("308", ((Set<NoSQLDocument>)result).iterator().next().getValue("model.modèle"));
    }

    /**
     * Test of getNbrDocuments method, of class NoSQLManager.
     */
    @Test
    public void testGetNbrDocuments_0args() {
        assertEquals(0, new NoSQLManager().getNbrDocuments());
        assertEquals(1, instance.getNbrDocuments());
    }

    /**
     * Test of getNbrDocuments method, of class NoSQLManager.
     */
    @Test
    public void testGetNbrDocuments_String() {
        assertEquals(0, new NoSQLManager().getNbrDocuments("db"));
        assertEquals(1, instance.getNbrDocuments("db"));
        assertEquals(0, instance.getNbrDocuments("not.exist"));
    }
}
