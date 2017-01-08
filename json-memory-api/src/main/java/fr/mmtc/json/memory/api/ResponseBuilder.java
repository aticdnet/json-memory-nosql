/*
 * json-memory-api
 * ===============
 * 
 * ResponseBuilder - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api;

import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseError;
import fr.mmtc.json.memory.api.response.ResponseSuccess;
import fr.mmtc.json.memory.api.response.ResponseSuccess.TypeSuccessResponse;

/**
 * Builder for a response of NoSql Query
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public final class ResponseBuilder {

    /**
     * Hide default constructor
     */
    private ResponseBuilder() {
        
    }
    
    /**
     * Create a success response
     * @param typeResponse type of success response
     * @return success response
     */
    public static IResponse success(TypeSuccessResponse typeResponse) {
        return new ResponseSuccess(typeResponse);
    }
    
    /**
     * Create a success response
     * @param typeResponse type of success response
     * @param object result of a query
     * @return success response
     */
    public static IResponse success(TypeSuccessResponse typeResponse, Object object) {
        return new ResponseSuccess(typeResponse, object);
    }
    
    /**
     * Create an error response
     * @param error error message
     * @return error response
     */
    public static IResponse error(Exception error) {
        return new ResponseError(error);
    }
}
