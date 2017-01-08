/*
 * json-memory-api
 * ===============
 * 
 * UniqueQuery - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.query;

/**
 * Unique value for query
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class UniqueValue {
    private String fieldPathName;

    /**
     * Get field path name
     * @return field path name
     */
    public String getFieldPathName() {
        return fieldPathName;
    }
    
    /**
     * Set field path name
     * @param fieldPathName field path name 
     */
    public void setFieldPathName(String fieldPathName) {
        this.fieldPathName = fieldPathName;
    }
}
