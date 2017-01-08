/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLResources - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql.web;

import fr.mmtc.json.memory.api.ResponseBuilder;
import fr.mmtc.json.memory.api.query.SearchQuery;
import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseSuccess;
import fr.mmtc.json.memory.manager.NoSQLManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import java.io.IOException;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Resources of this microservices
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Path("/")
@Api(value = "NoSQL Memory resources")
@Produces(MediaType.APPLICATION_JSON)
public class NoSQLResources {

    @Inject
    private NoSQLManager noSQLManager;
    
    /**
     * Default constructor
     */
    public NoSQLResources() {
        // default constructor
    }

    NoSQLResources(NoSQLManager noSQLManager) {
        this.noSQLManager = noSQLManager;
    }
    
    
    
    /**
     * Add a document in repository
     *
     * @param repository repository name
     * @param document document
     * @return IResponse
     * @throws IOException
     */
    @POST
    @Path("/{repository}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add new document into a repository", httpMethod = "POST", 
            response = IResponse.class)
    @ApiResponse(code = 200, message = "Default return code. If success, response is ResponseSuccess class. If failed, response is ResponseError class")
    public IResponse addDocument(@ApiParam(value = "Repository name", required = true) @PathParam("repository") String repository, @ApiParam(value = "Document in json format", required = true) String document) throws IOException {
        try {
            return noSQLManager.loadJSON(repository, document);
        } catch (IOException ex) {
            return ResponseBuilder.error(ex);
        }
    }

    /**
     * Return result of a query
     *
     * @param searchQuery query
     * @return result of a query
     * @throws IOException
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Launch a Search Query into a repository", httpMethod = "POST",
            response = IResponse.class)
    @ApiResponse(code = 200, message = "Default return code. If success, response is ResponseSuccess class. If failed, response is ResponseError class")
    public IResponse searchQuery( @ApiParam(value = "Search query contains repository name in repositoryName field", required = true) SearchQuery searchQuery) throws IOException {
        try {
            return ResponseBuilder.success(ResponseSuccess.TypeSuccessResponse.SEARCHQUERY, noSQLManager.search(searchQuery));
        } catch (Exception ex) {
            return ResponseBuilder.error(ex);
        }
    }
}
