/*
 * json-memory-api
 * ===============
 * 
 * ResponseType - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.response;

/**
 * Enum of different response type
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public final class ResponseType {
    /**
     * Success response
     */
    public static final String SUCCESS = "success";
    /**
     * Error response
     */
    public static final String ERROR = "error";
    
    /**
     * Hide default constructor
     */
    private ResponseType() {
        
    }
}
