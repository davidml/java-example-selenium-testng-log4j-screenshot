/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author David
 */
public class Test1 {

    public Test1() {
    }

    @Test
    public void testHello() {
        System.out.println("testHello::Test Case One with Thread Id:- "
                + Thread.currentThread().getId());
    }

    @Test
    public void testHello1() {
        System.out.println("testHello1::Test Case One with Thread Id:- "
                + Thread.currentThread().getId());
        WebDriver driver = Utiles.getRemoteDriver("https://www.yahoo.com/");
        assertTrue(driver.getTitle().contains("Yahoo"),"no se ha encontrado el texto Yahoo");
        driver.quit();
    }

    @org.testng.annotations.BeforeTest
    public static void setUpTest() throws Exception {
    }
    
    @org.testng.annotations.AfterTest
    public static void tearDownTest() throws Exception {
    }
    
}
