/*
 * json-memory-api
 * ===============
 * 
 * SearchQuery - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.api.query;

/**
 * Search query
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class SearchQuery {
    private String repositoryName;
    private UniqueValue uniqueValue;
    private FilterQuery filterQuery;

    /**
     * Get UniqueValue instance
     * @return UniqueValue instance
     */
    public UniqueValue getUniqueValue() {
        return uniqueValue;
    }

    /**
     * Set UniqueValue instance
     * @param uniqueValue UniqueValue instance
     */
    public void setUniqueValue(UniqueValue uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    /**
     * Get repository name
     * @return repository name
     */
    public String getRepositoryName() {
        return repositoryName;
    }

    /**
     * Set repository name
     * @param repositoryName repository name 
     */
    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    /**
     * Get FilterQuery instance
     * @return FilterQuery instance
     */
    public FilterQuery getFilterQuery() {
        return filterQuery;
    }

    /**
     * Set FilterQuery instance
     * @param filterQuery FilterQuery instance
     */
    public void setFilterQuery(FilterQuery filterQuery) {
        this.filterQuery = filterQuery;
    }
}
