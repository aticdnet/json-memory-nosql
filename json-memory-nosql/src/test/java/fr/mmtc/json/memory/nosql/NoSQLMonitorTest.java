/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLMonitorTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory.nosql;

import fr.mmtc.UnitTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * NoSQLMonitor Unit TTest
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLMonitorTest {
    
    public NoSQLMonitorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class NoSQLMonitor.
     */
    @Test
    public void testStart() {
        NoSQLMonitor result = NoSQLMonitor.start("message", new Object[]{});
        assertNotNull(result);
    }

    /**
     * Test of end method, of class NoSQLMonitor.
     */
    @Test
    public void testEnd() {
        NoSQLMonitor result = NoSQLMonitor.start("message", new Object[]{});
        assertNotNull(result);
        result.end("message", new Object[]{});
    }    
}
