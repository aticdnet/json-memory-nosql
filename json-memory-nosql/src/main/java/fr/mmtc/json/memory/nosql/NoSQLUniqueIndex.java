/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLUniqueIndex - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manage unique indexes
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class NoSQLUniqueIndex implements Serializable {
    
    private final NoSQLDocuments noSQLDocuments;
    private final ConcurrentHashMap<String, Set<String>> indexes;
    
    /**
     * Create documents unique indexes
     * @param noSQLDocuments 
     */
    public NoSQLUniqueIndex(NoSQLDocuments noSQLDocuments) {
        this.noSQLDocuments = noSQLDocuments;
        this.indexes = new ConcurrentHashMap<>();
    }

    /**
     * Update indexes by this document
     * @param document 
     */
    public void updateIndex(NoSQLDocument document) {
        indexes.keySet().forEach((key) -> {
            NoSQLMonitor noSQLMonitor = NoSQLMonitor.start("Update unique index {0} for {1}", new Object[]{key, noSQLDocuments.getName()});
            Set<String> listUniqueString = indexes.get(key);
            String value = document.getValue(key);
            if (value != null) {
                listUniqueString.add(value);
            }
            noSQLMonitor.end("Unique index {0} for {1} updated in {2}ms with {3} mo memory used", new Object[]{key, noSQLDocuments.getName()});
        });
    }
    
    /**
     * Returns unique values for key
     * @param key
     * @return unique values for key
     */
    public Set<String> getUniqueValuesByKey(String key) {
        if (!indexes.containsKey(key)) {
            NoSQLMonitor noSQLMonitor = NoSQLMonitor.start("Create unique index {0} for {1}", new Object[]{key, noSQLDocuments.getName()});
            Set<String> listUniqueString = new HashSet<>();
            noSQLDocuments.getDocuments().forEach((document) -> {
                String value = document.getValue(key);
                if (value != null) {
                    listUniqueString.add(value);
                } 
            });
            indexes.put(key, listUniqueString);
            noSQLMonitor.end("Unique index {0} for {1} created in {2}ms with {3} mo memory used", new Object[]{key, noSQLDocuments.getName()});
        }
        return indexes.get(key);
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
