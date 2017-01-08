/*
 * json-memory-api
 * ===============
 * 
 * FilterQuery - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.query;

/**
 * Filter for a search query
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class FilterQuery extends UniqueValue {
    private String value;

    /**
     * Get value
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set value
     * @param value value 
     */
    public void setValue(String value) {
        this.value = value;
    }
}
