/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

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
public class ClientNGTest {
    
    public ClientNGTest() {
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
     * Test of getRegNo method, of class Client.
     */
    @Test
    public void testGetRegNo() {
        System.out.println("getRegNo");
        Client instance = new Client();
        String expResult = "";
        String result = instance.getRegNo();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRegNo method, of class Client.
     */
    @Test
    public void testSetRegNo() {
        System.out.println("setRegNo");
        String regNo = "";
        Client instance = new Client();
        instance.setRegNo(regNo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientcategory method, of class Client.
     */
    @Test
    public void testGetClientcategory() {
        System.out.println("getClientcategory");
        Client instance = new Client();
        Clientcategory expResult = null;
        Clientcategory result = instance.getClientcategory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClientcategory method, of class Client.
     */
    @Test
    public void testSetClientcategory() {
        System.out.println("setClientcategory");
        Clientcategory clientcategory = null;
        Client instance = new Client();
        instance.setClientcategory(clientcategory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Client.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Client instance = new Client();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class Client.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "";
        Client instance = new Client();
        instance.setFirstName(firstName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Client.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Client instance = new Client();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class Client.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "";
        Client instance = new Client();
        instance.setLastName(lastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhoneNumber method, of class Client.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        Client instance = new Client();
        String expResult = "";
        String result = instance.getPhoneNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhoneNumber method, of class Client.
     */
    @Test
    public void testSetPhoneNumber() {
        System.out.println("setPhoneNumber");
        String phoneNumber = "";
        Client instance = new Client();
        instance.setPhoneNumber(phoneNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Client.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Client instance = new Client();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Client.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Client instance = new Client();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhoto method, of class Client.
     */
    @Test
    public void testGetPhoto() {
        System.out.println("getPhoto");
        Client instance = new Client();
        String expResult = "";
        String result = instance.getPhoto();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhoto method, of class Client.
     */
    @Test
    public void testSetPhoto() {
        System.out.println("setPhoto");
        String photo = "";
        Client instance = new Client();
        instance.setPhoto(photo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
