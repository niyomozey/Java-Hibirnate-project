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
public class BookNGTest {
    
    public BookNGTest() {
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
     * Test of getBookId method, of class Book.
     */
    @Test
    public void testGetBookId() {
        System.out.println("getBookId");
        Book instance = new Book();
        String expResult = "";
        String result = instance.getBookId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBookId method, of class Book.
     */
    @Test
    public void testSetBookId() {
        System.out.println("setBookId");
        String bookId = "";
        Book instance = new Book();
        instance.setBookId(bookId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBookcategory method, of class Book.
     */
    @Test
    public void testGetBookcategory() {
        System.out.println("getBookcategory");
        Book instance = new Book();
        Bookcategory expResult = null;
        Bookcategory result = instance.getBookcategory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBookcategory method, of class Book.
     */
    @Test
    public void testSetBookcategory() {
        System.out.println("setBookcategory");
        Bookcategory bookcategory = null;
        Book instance = new Book();
        instance.setBookcategory(bookcategory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Book.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Book instance = new Book();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Book.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        Book instance = new Book();
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPublishingHouse method, of class Book.
     */
    @Test
    public void testGetPublishingHouse() {
        System.out.println("getPublishingHouse");
        Book instance = new Book();
        String expResult = "";
        String result = instance.getPublishingHouse();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPublishingHouse method, of class Book.
     */
    @Test
    public void testSetPublishingHouse() {
        System.out.println("setPublishingHouse");
        String publishingHouse = "";
        Book instance = new Book();
        instance.setPublishingHouse(publishingHouse);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateOfPublication method, of class Book.
     */
    @Test
    public void testGetDateOfPublication() {
        System.out.println("getDateOfPublication");
        Book instance = new Book();
        Date expResult = null;
        Date result = instance.getDateOfPublication();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateOfPublication method, of class Book.
     */
    @Test
    public void testSetDateOfPublication() {
        System.out.println("setDateOfPublication");
        Date dateOfPublication = null;
        Book instance = new Book();
        instance.setDateOfPublication(dateOfPublication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthor method, of class Book.
     */
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
        Book instance = new Book();
        String expResult = "";
        String result = instance.getAuthor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthor method, of class Book.
     */
    @Test
    public void testSetAuthor() {
        System.out.println("setAuthor");
        String author = "";
        Book instance = new Book();
        instance.setAuthor(author);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPages method, of class Book.
     */
    @Test
    public void testGetPages() {
        System.out.println("getPages");
        Book instance = new Book();
        Integer expResult = null;
        Integer result = instance.getPages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPages method, of class Book.
     */
    @Test
    public void testSetPages() {
        System.out.println("setPages");
        Integer pages = null;
        Book instance = new Book();
        instance.setPages(pages);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
