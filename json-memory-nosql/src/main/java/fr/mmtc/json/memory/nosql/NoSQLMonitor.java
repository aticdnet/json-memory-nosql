/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLMonitor - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Monitor NoSQL activity
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class NoSQLMonitor {
    
    private Long start;
    private Long usedStart;
    private static final Logger LOGGER = Logger.getLogger(NoSQLMonitor.class.getName());
    
    private NoSQLMonitor() {
        
    }
    
    /**
     * Start monitor
     * @param message log message
     * @param params parameters for log message
     * @return start monitor
     */
    public static NoSQLMonitor start(String message, Object[] params) {
        NoSQLMonitor instance = new NoSQLMonitor();
        instance.start = System.currentTimeMillis();
        instance.usedStart = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        LOGGER.log(Level.INFO, message, params);
        return instance;
    }
    
    /**
     * End monitor
     * @param message log message 
     * @param params parameters for log message
     */
    public void end(String message, Object[] params) {
        Long usedEnd = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Long end = System.currentTimeMillis();
        ArrayList<Object> paramsExtended = new ArrayList<>(Arrays.asList(params));
        paramsExtended.add(end - start);
        paramsExtended.add((usedEnd - usedStart) / 1024 / 1024);
        LOGGER.log(Level.INFO, message, paramsExtended.toArray());
    }
}
