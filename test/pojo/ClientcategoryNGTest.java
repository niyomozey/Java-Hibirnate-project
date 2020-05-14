/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.Set;
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
public class ClientcategoryNGTest {
    
    public ClientcategoryNGTest() {
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
     * Test of getCategoryName method, of class Clientcategory.
     */
    @Test
    public void testGetCategoryName() {
        System.out.println("getCategoryName");
        Clientcategory instance = new Clientcategory();
        String expResult = "";
        String result = instance.getCategoryName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryName method, of class Clientcategory.
     */
    @Test
    public void testSetCategoryName() {
        System.out.println("setCategoryName");
        String categoryName = "";
        Clientcategory instance = new Clientcategory();
        instance.setCategoryName(categoryName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryId method, of class Clientcategory.
     */
    @Test
    public void testGetCategoryId() {
        System.out.println("getCategoryId");
        Clientcategory instance = new Clientcategory();
        String expResult = "";
        String result = instance.getCategoryId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryId method, of class Clientcategory.
     */
    @Test
    public void testSetCategoryId() {
        System.out.println("setCategoryId");
        String categoryId = "";
        Clientcategory instance = new Clientcategory();
        instance.setCategoryId(categoryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClients method, of class Clientcategory.
     */
    @Test
    public void testGetClients() {
        System.out.println("getClients");
        Clientcategory instance = new Clientcategory();
        Set expResult = null;
        Set result = instance.getClients();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClients method, of class Clientcategory.
     */
    @Test
    public void testSetClients() {
        System.out.println("setClients");
        Set clients = null;
        Clientcategory instance = new Clientcategory();
        instance.setClients(clients);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
