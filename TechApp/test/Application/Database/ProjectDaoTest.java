/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

import Application.Metier.Project;
import Application.Metier.ProjectStatus;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Eileen
 */
public class ProjectDaoTest {
    
    public ProjectDaoTest() {
    }

    /**
     * Test of listAll method, of class ProjectDao. (utilisé lorsque la fonction était publique)
     */
//    @Test
//    public void testStringToProjectStatus() throws Exception{
//        System.out.println("StringToPS");
//        ProjectDao instance = new ProjectDao();
//        ProjectStatus result = instance.stringToProjectStatus("fini");
//        ProjectStatus expResult = ProjectStatus.ENDED;
//        assertEquals(expResult, result);
//        } 
    
    @Test
    public void testListAll() throws Exception{
        System.out.println("ListAll");
        ProjectDao instance = new ProjectDao();
        ArrayList<Project> projects = instance.listAll();
        Project projet1 = projects.get(0);
        String result = projet1.getName();
        String expResult = "Test Update";
        
        String res1_2 = projet1.materialsToString();
        String expRes1_2 = "souris 5 150.0 2019-08-01 | mamy choice 3 450.0 2020-07-28 | ";
        assertEquals(expRes1_2, res1_2);
        System.out.println(projet1.activitiesToString());
        assertEquals(expResult, result);
        
        projet1 = projects.get(1);
        result = projet1.getName();
        ProjectStatus eres2 = ProjectStatus.WAITING;
        ProjectStatus res2 = projet1.getStatus();
        expResult = "war gaming";
        
        assertEquals(expResult, result);
        assertEquals(eres2, res2);
        String res2_2 = projet1.materialsToString();
        String expRes2_2 = "sas 1 250.0 2020-06-01 | ";
        assertEquals(expRes2_2, res2_2);
        System.out.println(projet1.activitiesToString());
        
        projet1 = projects.get(2);
        result = projet1.getName();
        expResult = "bitcoin";
        eres2 = ProjectStatus.WORKING;
        res2 = projet1.getStatus();
        
        assertEquals(expResult, result);
        assertEquals(eres2, res2);
        String res3_2 = projet1.materialsToString();
        String expRes3_2 = "asus 1 150.0 2020-06-01 | raspberry 5 125.0 2019-06-17 | haut parleur 1 3000.0 2020-11-18 | ";
        assertEquals(expRes3_2, res3_2);
        System.out.println(projet1.activitiesToString());
    }
    
    @Test
    public void testUpdate() throws Exception{
        System.out.println("Update");
        ProjectDao instance = new ProjectDao();
        ArrayList<Project> projects = instance.listAll();
        Project project1 = projects.get(0);
        String expResult = "Test Update"; 
        project1.setName(expResult);
        instance.update(project1);
        
        projects = instance.listAll();
        project1 = projects.get(0);
        String result = project1.getName();
        
        assertEquals(expResult, result); 
    }
}
