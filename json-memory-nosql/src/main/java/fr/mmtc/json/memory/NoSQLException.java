/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLException - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory;

/**
 * Manage exception of nosql repository
 *
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
public class NoSQLException extends RuntimeException {
    
    /**
     * Constructor
     * 
     * @param message exception message 
     */
    public NoSQLException(String message) {
        super(message);
    }
}
