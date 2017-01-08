/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLManager - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.manager;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.mmtc.json.memory.api.ResponseBuilder;
import fr.mmtc.json.memory.api.query.SearchQuery;
import fr.mmtc.json.memory.api.response.IResponse;
import fr.mmtc.json.memory.api.response.ResponseSuccess.TypeSuccessResponse;
import fr.mmtc.json.memory.nosql.NoSQLDocuments;
import fr.mmtc.json.memory.nosql.NoSQLMonitor;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;

/**
 * Manage a nosql repository
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */

@ApplicationScoped
public class NoSQLManager {

    private final Map<String, NoSQLDocuments> documentses = new ConcurrentHashMap<>();
    
    /**
     * Load a list of document in json format
     *
     * @param documents list of documents in json format
     * @return IResponse
     * @throws IOException exception
     */
    public IResponse loadJSON(String documents) throws IOException {
        NoSQLMonitor noSQLMonitor = NoSQLMonitor.start("Start loading document ", new Object[]{});
        try {
            return addDocuments(documents);
        } finally {
            noSQLMonitor.end("Loading {0} documents in {1} ms with {2} mo memory used", new Object[]{getNbrDocuments()});
        }
    }

    /**
     * Load a list of document in json format
     *
     * @param repository repository name
     * @param document list of documents in json format
     * @return IResponse
     * @throws IOException exception
     */
    public IResponse loadJSON(String repository, String document) throws IOException {
        NoSQLMonitor noSQLMonitor = NoSQLMonitor.start("Add document {0}", new Object[]{document});
        try {
            return addDocuments(repository, document);
        } finally {
            noSQLMonitor.end("Chargement de {0} documents en {1} ms with {2} mo memory used", new Object[]{getNbrDocuments(repository)});
        }
    }
    
    /**
     * Clear all repository
     */
    public void clear() {
        documentses.clear();
    }

    /**
     * Return the result of query
     *
     * @param query query
     * @return result of query
     */
    public Object search(SearchQuery query) {
        if (query == null) {
            return null;
        }
        if (query.getRepositoryName() == null) {
            return null;
        }
        
        NoSQLDocuments noSQLDocuments = documentses.get(query.getRepositoryName());
        if (noSQLDocuments == null) {
            return null;
        }
        return search(query, noSQLDocuments);
    }
    
    private Object search(SearchQuery query, NoSQLDocuments noSQLDocuments) {
        if (query.getUniqueValue() != null) {
            return noSQLDocuments.searchByUniqueValue(query);
        }
        if (query.getFilterQuery() != null) {
            return noSQLDocuments.searchByFilterQuery(query);
        }
        return null;
    }

    private IResponse addDocuments(String documents) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        return addDocuments(mapper.readTree(documents));
    }

    private IResponse addDocuments(String repository, String document) throws IOException {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        return addDocuments(repository, mapper.readTree(document));
    }

    private IResponse addDocuments(JsonNode node) {
        addAllFields(node.fields());
        return ResponseBuilder.success(TypeSuccessResponse.ADDNEWDOCUMENTS);
    }

    private IResponse addDocuments(String repository, JsonNode node) {
        NoSQLDocuments documents = documentses.get(repository);
        if (documents == null) {
            documents = new NoSQLDocuments(repository);
            documentses.put(repository, documents);
        }
        documents.addDocuments(node);
        return ResponseBuilder.success(TypeSuccessResponse.ADDNEWDOCUMENT);
    }

    private void addAllFields(Iterator<Entry<String, JsonNode>> fieldsIterator) {
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
                NoSQLDocuments noSQLDocuments = documentses.get(field.getKey());
                if (noSQLDocuments == null) {        
                    noSQLDocuments = new NoSQLDocuments(field.getKey());
                }
                noSQLDocuments.addDocuments(field.getValue());
                documentses.put(field.getKey(), noSQLDocuments);
            }
        }
    }

    /**
     * Return number of document
     * 
     * @return Number of document
     */
    int getNbrDocuments() {
        Integer nbrDocument = 0;
        nbrDocument = documentses.values().stream().map((documents) -> documents.getNbrDocuments()).reduce(nbrDocument, Integer::sum);
        return nbrDocument;
    }
    
    /**
     * Return number of document in repository
     * @param repository
     * @return 
     */
    int getNbrDocuments(String repository) {
        NoSQLDocuments documents = documentses.get(repository);
        if (documents != null) {
            return documents.getNbrDocuments();
        } else {
            return 0;
        }
    }
}
