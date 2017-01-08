/*
 * json-memory-nosql
 * =================
 * 
 * NoSQLExceptionTest - Thierry COUNILH <thierry.counilh@aticdnet.com>
 * 
 * © Copyright 2016 @tIcdnet, Thierry COUNILH. Tous droits réservés.
 * Reproduction interdite sans l'autorisation écrite de l'auteur.
 */
package fr.mmtc.json.memory;

import fr.mmtc.UnitTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

/**
 * NoSQLException unit test
 * 
 * @author Thierry COUNILH <thierry.counilh@aticdnet.com>
 */
@Category(UnitTest.class)
public class NoSQLExceptionTest {
    
    public NoSQLExceptionTest() {
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

    @Test
    public void testSomeMethod() {
        NoSQLException exception = new NoSQLException("TestValue");
        assertEquals("TestValue", exception.getMessage());
        assertTrue(exception instanceof RuntimeException);
    }
    
}
