/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Metier;

import Application.Database.ProjectDao;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eileen
 */
public class ProjectTest {
    
    public ProjectTest() {
    }

    /**
     * Test of Start method, of class Project.
     */
//    @Test
//    public void testStart() throws Exception {
//        System.out.println("Start");
//        Project instance = new Project();
//        instance.Start();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of Cancel method, of class Project.
//     */
//    @Test
//    public void testCancel() throws Exception {
//        System.out.println("Cancel");
//        Project instance = new Project();
//        instance.Cancel();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setName method, of class Project.
//     */
//    @Test
//    public void testSetName() {
//        System.out.println("setName");
//        String aName = "";
//        Project instance = new Project();
//        instance.setName(aName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getName method, of class Project.
//     */
//    @Test
//    public void testGetName() {
//        System.out.println("getName");
//        Project instance = new Project();
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFinalDuration method, of class Project.
//     */
//    @Test
//    public void testSetFinalDuration() {
//        System.out.println("setFinalDuration");
//        int aFinalDuration = 0;
//        Project instance = new Project();
//        instance.setFinalDuration(aFinalDuration);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFinalDuration method, of class Project.
//     */
//    @Test
//    public void testGetFinalDuration() {
//        System.out.println("getFinalDuration");
//        Project instance = new Project();
//        int expResult = 0;
//        int result = instance.getFinalDuration();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStatus method, of class Project.
//     */
//    @Test
//    public void testSetStatus() {
//        System.out.println("setStatus");
//        ProjectStatus aStatus = null;
//        Project instance = new Project();
//        instance.setStatus(aStatus);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getStatus method, of class Project.
//     */
//    @Test
//    public void testGetStatus() {
//        System.out.println("getStatus");
//        Project instance = new Project();
//        ProjectStatus expResult = null;
//        ProjectStatus result = instance.getStatus();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getStatusString method, of class Project.
     */
    @Test
    public void testGetStatusString() throws Exception {
        System.out.println("getStatusString");
        ProjectDao instance = new ProjectDao();
        ArrayList<Project> projects = instance.listAll();
        Project projet1 = projects.get(0);
        String expResult = "Terminé";
        String result = projet1.getStatusString();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of setID method, of class Project.
//     */
//    @Test
//    public void testSetID() {
//        System.out.println("setID");
//        int aID = 0;
//        Project instance = new Project();
//        instance.setID(aID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getID method, of class Project.
//     */
//    @Test
//    public void testGetID() {
//        System.out.println("getID");
//        Project instance = new Project();
//        int expResult = 0;
//        int result = instance.getID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEstimatedDurationMinutes method, of class Project.
//     */
//    @Test
//    public void testSetEstimatedDurationMinutes() {
//        System.out.println("setEstimatedDurationMinutes");
//        int aEstimatedDurationMinutes = 0;
//        Project instance = new Project();
//        instance.setEstimatedDurationMinutes(aEstimatedDurationMinutes);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEstimatedDurationMinutes method, of class Project.
//     */
//    @Test
//    public void testGetEstimatedDurationMinutes() {
//        System.out.println("getEstimatedDurationMinutes");
//        Project instance = new Project();
//        int expResult = 0;
//        int result = instance.getEstimatedDurationMinutes();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEstimatedDurationHours method, of class Project.
//     */
//    @Test
//    public void testSetEstimatedDurationHours() {
//        System.out.println("setEstimatedDurationHours");
//        float aEstimatedDurationHours = 0.0F;
//        Project instance = new Project();
//        instance.setEstimatedDurationHours(aEstimatedDurationHours);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEstimatedDurationHours method, of class Project.
//     */
//    @Test
//    public void testGetEstimatedDurationHours() {
//        System.out.println("getEstimatedDurationHours");
//        Project instance = new Project();
//        float expResult = 0.0F;
//        float result = instance.getEstimatedDurationHours();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEstimatedDurationDays method, of class Project.
//     */
//    @Test
//    public void testSetEstimatedDurationDays() {
//        System.out.println("setEstimatedDurationDays");
//        float aEstimatedDurationDays = 0.0F;
//        Project instance = new Project();
//        instance.setEstimatedDurationDays(aEstimatedDurationDays);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEstimatedDurationDays method, of class Project.
//     */
//    @Test
//    public void testGetEstimatedDurationDays() {
//        System.out.println("getEstimatedDurationDays");
//        Project instance = new Project();
//        float expResult = 0.0F;
//        float result = instance.getEstimatedDurationDays();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Project.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Project instance = new Project();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
