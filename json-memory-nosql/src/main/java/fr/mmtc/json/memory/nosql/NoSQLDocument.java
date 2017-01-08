/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLDocument - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import com.fasterxml.jackson.databind.JsonNode;
import fr.mmtc.json.memory.api.query.FilterQuery;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Document structure
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class NoSQLDocument implements Serializable {

    private final ConcurrentHashMap<String, Object> item = new ConcurrentHashMap<>();

    /**
     * Constructor
     *
     * @param jsonNode document in json format
     */
    public NoSQLDocument(JsonNode jsonNode) {
        loadAllField(jsonNode, "");
    }

    /**
     * Return value of key
     *
     * @param key key
     * @return value of key
     */
    public String getValue(String key) {
        if (item.containsKey(key)) {
            return item.get(key).toString();
        }
        return null;
    }

    /**
     * Return if filter can be apply
     *
     * @param filterQuery filter of query
     * @return filter can ba apply ?
     */
    public boolean canApplyFilter(FilterQuery filterQuery) {
        return getValue(filterQuery.getFieldPathName()).equals(filterQuery.getValue());
    }

    private void loadAllField(JsonNode jsonNode, String path) {
        Iterator<Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();
        while (fieldsIterator.hasNext()) {
            Entry<String, JsonNode> field = fieldsIterator.next();
            if (field.getValue().isArray()) {
                item.put(path + field.getKey(), field.getValue());
            } else if (field.getValue().isObject()) {
                loadAllField(field.getValue(), path + field.getKey() + ".");
            } else {
                item.put(path + field.getKey(), field.getValue().asText());
            }
        }
    }
}
