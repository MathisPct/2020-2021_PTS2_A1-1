/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Metier;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author math7
 */
public class UtilsTest {
    
    public UtilsTest() {
    }

    /**
     * Test of HashPassword method, of class Utils.
     */
    @Test
    public void testHashPassword() {
        String passWordAHacher = "toto";
        String passWordHache = "746F746F"; //toto en texte
        assertEquals(passWordHache.toLowerCase(), Utils.HashPassword(passWordAHacher).toLowerCase());
        assertEquals("6d6174686973", Utils.HashPassword("mathis"));
    }

    /**
     * Test of IsPasswordSafe method, of class Utils.
     */
    @Test
    public void testIsPasswordSafe() {
    }
    
}
