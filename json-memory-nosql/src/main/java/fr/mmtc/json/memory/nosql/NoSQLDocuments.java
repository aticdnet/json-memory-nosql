/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLDocuments - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mmtc.json.memory.api.query.SearchQuery;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * List of document
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class NoSQLDocuments implements Serializable  {
    
    private final String name;
    private final Queue<NoSQLDocument> documents = new ConcurrentLinkedQueue<>();
    private final NoSQLUniqueIndex uniqueValuesByKey = new NoSQLUniqueIndex(this);
    private final NoSQLIndex indexByKey = new NoSQLIndex(this);
    
    /**
     * Constructor
     * @param repository repository of documents list 
     */
    public NoSQLDocuments(String repository) {
        this.name = repository;
    }
    
    /**
     * Return name of this repository
     * @return name of this repository
     */
    public String getName() {
        return name;
    }
    
    /**
     * Return all documents of this repository
     * @return documents of this repository
     */
    public Queue<NoSQLDocument> getDocuments() {
        return documents;
    }
    
    /**
     * Clear this repository
     */
    public void clear() {
        documents.clear();
        uniqueValuesByKey.clear();
        indexByKey.clear();
    }

    /**
     * Add a list of document
     * @param node list of document in json format
     */
    public void addDocuments(JsonNode node)  {
        if (node.isArray()) {
            Iterator<JsonNode> elements = node.elements();
            while (elements.hasNext()) {
                JsonNode jsonNode = elements.next();
                if (jsonNode.isObject()) {
                    NoSQLDocument document = new NoSQLDocument(jsonNode);
                    uniqueValuesByKey.updateIndex(document);
                    indexByKey.updateIndex(document);
                    documents.add(document);
                }
            }
        } else if (node.isObject()) {
            NoSQLDocument document = new NoSQLDocument(node);
            uniqueValuesByKey.updateIndex(document);
            indexByKey.updateIndex(document);
            documents.add(document);
        }
    }
    
    /**
     * Return number of document in list
     * @return 
     */
    public int getNbrDocuments() {
        return documents.size();
    }

    /**
     * Return result unique set of query
     * @param query query
     * @return 
     */
    public Set<String> searchByUniqueValue(SearchQuery query) {
        return uniqueValuesByKey.getUniqueValuesByKey(query.getUniqueValue().getFieldPathName());
    }
    
    /**
     * Return result filter set of query
     * @param query query
     * @return 
     */
    public Set<NoSQLDocument> searchByFilterQuery(SearchQuery query) {
        return indexByKey.getValuesByKey(query.getFilterQuery().getFieldPathName(), query.getFilterQuery().getValue());
    }
}
