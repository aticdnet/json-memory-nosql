/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLResourcesITTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql.web;

import fr.mmtc.IntegrationTest;
import fr.mmtc.json.memory.api.query.UniqueValue;
import fr.mmtc.json.memory.api.query.SearchQuery;
import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseSuccess;
import fr.mmtc.json.memory.manager.NoSQLManager;
import fr.mmtc.json.memory.nosql.NoSQLDocument;
import fr.mmtc.json.memory.nosql.NoSQLDocuments;
import fr.mmtc.json.memory.nosql.NoSQLIndex;
import fr.mmtc.json.memory.nosql.NoSQLMonitor;
import fr.mmtc.json.memory.nosql.NoSQLUniqueIndex;
import java.net.URL;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 * NoSQLResources Integrated Test
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(IntegrationTest.class)
@RunWith(Arquillian.class)
public class NoSQLResourcesITTest  {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Deployment(testable = true)
    public static Archive createDeployment() throws Exception {
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addResource(NoSQLDocument.class );
        deployment.addResource(NoSQLDocuments.class );
        deployment.addResource(NoSQLMonitor.class );
        deployment.addResource(NoSQLIndex.class );
        deployment.addResource(NoSQLUniqueIndex.class );
        deployment.addResource(NoSQLManager.class );
        deployment.addResource(NoSQLResources.class );
        deployment.addPackage("fr.mmtc.json");
        deployment.addAllDependencies();
        return deployment;
    }
    
    @CreateSwarm
    public static Swarm newContainer() throws Exception {
        Swarm swarm = new Swarm();
        return swarm;
    }
    
    @Test
    @RunAsClient
    @InSequence(1)
    public void testAdd308(@ArquillianResource URL url) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String db308  = IOUtils.toString(classLoader.getResource("308.json").openStream(), "UTF-8");
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url.toString()).path("cars");
        
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(db308, MediaType.APPLICATION_JSON), Response.class);

        Assert.assertEquals(200, response.getStatus());
        IResponse iresponse = response.readEntity(IResponse.class);
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.ADDNEWDOCUMENT, responseSuccess.getTypeResponse());
    }
    
    @Test
    @RunAsClient
    @InSequence(2)
    public void testSearchQueryWithoutQuery(@ArquillianResource URL url) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url.toString());
        
        target = target.path("");
        
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(null, MediaType.APPLICATION_JSON), Response.class);
        Assert.assertEquals(200, response.getStatus());
        IResponse iresponse = response.readEntity(IResponse.class);
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNull(responseSuccess.getResult());
    }
    
    @Test
    @RunAsClient
    @InSequence(3)
    public void testSearchQueryWithoutRepository(@ArquillianResource URL url) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url.toString());
        
        target = target.path("");
        
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new SearchQuery(), MediaType.APPLICATION_JSON), Response.class);
        Assert.assertEquals(200, response.getStatus());
        IResponse iresponse = response.readEntity(IResponse.class);
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNull(responseSuccess.getResult());
    }
    
    @Test
    @RunAsClient
    @InSequence(3)
    public void testSearchQueryMarque(@ArquillianResource URL url) throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url.toString());
        
        target = target.path("");
        
        SearchQuery query = new SearchQuery();
        query.setRepositoryName("cars");
        query.setUniqueValue(new UniqueValue());
        query.getUniqueValue().setFieldPathName("model.marque");
        
        
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(query, MediaType.APPLICATION_JSON), Response.class);
        Assert.assertEquals(200, response.getStatus());
        IResponse iresponse = response.readEntity(IResponse.class);
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        ResponseSuccess responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNotNull(responseSuccess.getResult());
        Assert.assertArrayEquals(new String[] {"Peugeot"}, ((ArrayList)responseSuccess.getResult()).toArray());
        testAdd308(url);
        
        response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(query, MediaType.APPLICATION_JSON), Response.class);
        Assert.assertEquals(200, response.getStatus());
        iresponse = response.readEntity(IResponse.class);
        Assert.assertTrue(iresponse instanceof ResponseSuccess);
        responseSuccess = (ResponseSuccess)iresponse;
        Assert.assertEquals(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, responseSuccess.getTypeResponse());
        Assert.assertNotNull(responseSuccess.getResult());
        Assert.assertArrayEquals(new String[] {"Peugeot"}, ((ArrayList)responseSuccess.getResult()).toArray());
    }
}
