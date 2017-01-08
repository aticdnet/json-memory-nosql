/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLIndex - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manage document indexes
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class NoSQLIndex implements Serializable {

    private final NoSQLDocuments noSQLDocuments;
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Set<NoSQLDocument>>> indexes;

    /**
     * Create documents indexes
     * @param noSQLDocuments documents
     */
    public NoSQLIndex(NoSQLDocuments noSQLDocuments) {
        this.noSQLDocuments = noSQLDocuments;
        this.indexes = new ConcurrentHashMap<>();
    }

    /**
     * Update indexes by this document
     * @param document document
     */
    public void updateIndex(NoSQLDocument document) {
        indexes.keySet().forEach((key) -> {
            NoSQLMonitor noSQLMonitor = NoSQLMonitor.start("Update index {0} for {1}", new Object[]{key, noSQLDocuments.getName()});
            ConcurrentHashMap<String, Set<NoSQLDocument>> listDocuments = indexes.get(key);
            String valueFromDocument = document.getValue(key);
            Set<NoSQLDocument> listDocument;
            if (!listDocuments.containsKey(valueFromDocument)) {
                listDocument = new HashSet<>();
                listDocument.add(document);
                listDocuments.put(valueFromDocument, listDocument);
            } else {
                listDocument = listDocuments.get(valueFromDocument);
                listDocument.add(document);
            }
            noSQLMonitor.end("Index {0} for {1} updated in {2}ms with {3} mo memory used", new Object[]{key, noSQLDocuments.getName()});
        });
    }

    /**
     * Returns the documents that have the value of the key
     * @param key key
     * @param value value
     * @return the documents that have the value of the key
     */
    public Set<NoSQLDocument> getValuesByKey(String key, String value) {
        if (!indexes.containsKey(key)) {
            NoSQLMonitor noSQLMonitor = NoSQLMonitor.start("Create index {0} for {1}", new Object[]{key, noSQLDocuments.getName()});
            ConcurrentHashMap<String, Set<NoSQLDocument>> listDocuments = new ConcurrentHashMap<>();
            noSQLDocuments.getDocuments().forEach((document) -> {
                String valueFromDocument = document.getValue(key);
                Set<NoSQLDocument> listDocument;
                if (!listDocuments.containsKey(valueFromDocument)) {
                    listDocument = new HashSet<>();
                    listDocument.add(document);
                    listDocuments.put(valueFromDocument, listDocument);
                } else {
                    listDocument = listDocuments.get(valueFromDocument);
                    listDocument.add(document);
                }
            });
            indexes.put(key, listDocuments);
            noSQLMonitor.end("Index {0} for {1} created in {2}ms with {3} mo memory used", new Object[]{key, noSQLDocuments.getName()});
        }
        return indexes.get(key).get(value);
    }

    /**
     * Clear all indexes
     */
    public void clear() {
        indexes.clear();
    }
    
    int getNbrIndexes() {
        Integer nbrDocument = 0;
        nbrDocument = indexes.keySet().stream().map((key) -> indexes.get(key).size()).reduce(nbrDocument, Integer::sum);
        return nbrDocument;
    }
}
