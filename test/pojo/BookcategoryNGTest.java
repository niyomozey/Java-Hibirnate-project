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
public class BookcategoryNGTest {
    
    public BookcategoryNGTest() {
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
     * Test of getCategoryName method, of class Bookcategory.
     */
    @Test
    public void testGetCategoryName() {
        System.out.println("getCategoryName");
        Bookcategory instance = new Bookcategory();
        String expResult = "";
        String result = instance.getCategoryName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryName method, of class Bookcategory.
     */
    @Test
    public void testSetCategoryName() {
        System.out.println("setCategoryName");
        String categoryName = "";
        Bookcategory instance = new Bookcategory();
        instance.setCategoryName(categoryName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryId method, of class Bookcategory.
     */
    @Test
    public void testGetCategoryId() {
        System.out.println("getCategoryId");
        Bookcategory instance = new Bookcategory();
        String expResult = "";
        String result = instance.getCategoryId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryId method, of class Bookcategory.
     */
    @Test
    public void testSetCategoryId() {
        System.out.println("setCategoryId");
        String categoryId = "";
        Bookcategory instance = new Bookcategory();
        instance.setCategoryId(categoryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBooks method, of class Bookcategory.
     */
    @Test
    public void testGetBooks() {
        System.out.println("getBooks");
        Bookcategory instance = new Bookcategory();
        Set expResult = null;
        Set result = instance.getBooks();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBooks method, of class Bookcategory.
     */
    @Test
    public void testSetBooks() {
        System.out.println("setBooks");
        Set books = null;
        Bookcategory instance = new Bookcategory();
        instance.setBooks(books);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
