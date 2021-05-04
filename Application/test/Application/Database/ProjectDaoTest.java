/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Database;

import Application.Metier.Project;
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
        
        assertEquals(expResult, result);
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
