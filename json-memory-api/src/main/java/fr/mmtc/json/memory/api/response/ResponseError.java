/*
 * json-memory-api
 * ===============
 * 
 * ResponseError - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.response;

/**
 * Error response
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class ResponseError implements IResponse {
    private final String resultStatus;
    private String message;
    
    /**
     * Constructor
     */
    public ResponseError() {
        resultStatus = ResponseType.ERROR;
    }
    
    /**
     * Constructor
     * @param ex exception message
     */
    public ResponseError(Exception ex) {
        resultStatus = ResponseType.ERROR;
        message = ex.getMessage();
    }

    @Override
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * Return error message 
     * @return error message
     */
    public String getMessage() {
        return message;
    }
}
