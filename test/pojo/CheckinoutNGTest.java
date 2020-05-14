/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Niyonkuru Moise
 */
public class CheckinoutNGTest {
    
    public CheckinoutNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getNames method, of class Checkinout.
     */
    @Test
    public void testGetNames() {
        System.out.println("getNames");
        Checkinout instance = new Checkinout();
        String expResult = "";
        String result = instance.getNames();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNames method, of class Checkinout.
     */
    @Test
    public void testSetNames() {
        System.out.println("setNames");
        String names = "";
        Checkinout instance = new Checkinout();
        instance.setNames(names);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateTime method, of class Checkinout.
     */
    @Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        Checkinout instance = new Checkinout();
        Date expResult = null;
        Date result = instance.getDateTime();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateTime method, of class Checkinout.
     */
    @Test
    public void testSetDateTime() {
        System.out.println("setDateTime");
        Date dateTime = null;
        Checkinout instance = new Checkinout();
        instance.setDateTime(dateTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Checkinout.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Checkinout instance = new Checkinout();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class Checkinout.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "";
        Checkinout instance = new Checkinout();
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
