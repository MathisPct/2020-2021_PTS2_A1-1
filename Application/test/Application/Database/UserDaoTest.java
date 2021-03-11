/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

import Application.Metier.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mathis Poncet
 */
public class UserDaoTest {
    
    public UserDaoTest() {
    }

    /**
     * @autor Mathis Poncet
     * Test de la fonction Read avec les données de la table utilisateur
     * 
     */
    @Test
    public void testRead() throws Exception {
        UserDao con = new UserDao();
        User sdjamel = new User(5); //user avec l'id 5 dans la table utilisateur
        User pmartin = new User(1); //user avec l'id 1 dans la table utilisateur
        User gdespierres2 = new User(24); //user avec l'id 24 dans la table utilisateur
        /**
         * On vérifie que la Read lit bien le bon user depuis la BD
         */
        assertEquals(sdjamel.getID(),con.Read("sdjamel", "746F746F").getID());
        assertEquals(pmartin.getID(),con.Read("pmartin", "746F746F").getID());
        assertEquals(gdespierres2.getID(),con.Read("gdespierres2", "746F746F").getID());
        
        /**
         * L'utilisateur cherché avec la fonction Read correspond à l'user
         * avec l'id 1 dans la table. u1 a un id de 5. Les 2 ne doivent pas être
         * egaux
         */
        assertNotEquals(sdjamel.getID(), con.Read("pmartin", "746F746F").getID());
    }

    /**
     * Test of Update method, of class UserDao.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of ListTechs method, of class UserDao.
     */
    @Test
    public void testListTechs() {
    }
    
}
