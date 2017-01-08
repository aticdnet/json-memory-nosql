/*
 * json-memory-api
 * ===============
 * 
 * ResponseSuccess - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.response;

/**
 * Success response
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class ResponseSuccess implements IResponse {
    
    /**
     * Type of success response
     */
    public enum TypeSuccessResponse {
        /**
         * Response for an additionnal new documents
         */
        ADDNEWDOCUMENTS,
        /**
         * Response for an additionnal new document
         */
        ADDNEWDOCUMENT,
        /**
         * Response for a searchQuery
         */
        SEARCHQUERY;
    }
    
    private final String resultStatus;
    private Object result;
    private final TypeSuccessResponse typeResponse;
    
    /**
     * Constructor
     */
    public ResponseSuccess() {
        this.resultStatus = ResponseType.SUCCESS;
        this.typeResponse = null;
    }
    
    /**
     * Constructor
     * @param typeResponse type of response
     */
    public ResponseSuccess(TypeSuccessResponse typeResponse) {
        this.resultStatus = ResponseType.SUCCESS;
        this.typeResponse = typeResponse;
    }
    
    /**
     * Constructor
     * @param typeResponse type of response
     * @param object
     */
    public ResponseSuccess(TypeSuccessResponse typeResponse, Object object) {
        this.resultStatus = ResponseType.SUCCESS;
        this.result = object;
        this.typeResponse = typeResponse;
    }

    @Override
    public  String getResultStatus() {
        return resultStatus;
    }

    /**
     * Return type of response
     * @return type of response
     */
    public TypeSuccessResponse getTypeResponse() {
        return typeResponse;
    }

    /**
     * Return result of a query
     * @return result of a query 
     */
    public Object getResult() {
        return result;
    }
}
