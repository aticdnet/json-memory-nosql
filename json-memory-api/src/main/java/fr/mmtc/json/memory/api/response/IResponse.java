/*
 * json-memory-api
 * ===============
 * 
 * IResponse - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Interface of response
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@JsonTypeInfo(  
    use = JsonTypeInfo.Id.NAME,  
    include = JsonTypeInfo.As.EXISTING_PROPERTY,  
    property = "resultStatus")
@JsonSubTypes({  
    @Type(value = ResponseSuccess.class, name = ResponseType.SUCCESS),  
    @Type(value = ResponseError.class, name = ResponseType.ERROR) })
public interface IResponse {
    /**
     * Return the status of this result
     * @return status of this result
     */
    String getResultStatus();
}
