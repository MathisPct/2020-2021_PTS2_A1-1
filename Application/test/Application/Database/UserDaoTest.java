/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

import Application.Metier.Tech;
import Application.Metier.User;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

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
    public void testListTechs() throws SQLException, ClassNotFoundException {
        UserDao con = new UserDao();
        Tech tech1 = new Tech(1); //Pierre dans la table technicien de la BDD
        Tech tech2 = new Tech(2); //Charles dans la table utilisateur de la BDD
        Tech tech3 = new Tech(3); //Yasmina dans la table utilisateur de la BDD
        Tech tech4 = new Tech(4); //Sarah dans la table utilisateur de la BDD
        Tech tech5 = new Tech(5); //Sabri dans la table utilisateur de la BDD
        tech1.setFirstName("Pierre");
        tech2.setFirstName("Charles");
        tech3.setFirstName("Yasmina");
        tech4.setFirstName("Sarah");
        tech5.setFirstName("Sabri");
        ArrayList<Tech> listTestTech = new ArrayList<>();
        listTestTech.add(tech1);
        listTestTech.add(tech2);
        listTestTech.add(tech3);
        listTestTech.add(tech4);
        listTestTech.add(tech5);
        //parcours des listes, on vérifie que les techniciens des 2 listes correspondent bien
        for (int i = 0; i < 5; i++) {
            assertEquals(listTestTech.get(i).getFirstName(), con.ListTechs().get(i).getFirstName());
        }
    }
    
}
