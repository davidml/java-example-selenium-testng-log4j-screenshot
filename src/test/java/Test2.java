
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author David
 */
public class Test2 {

    

    public Test2() {
    }

    @Test
    public void test2Hello3() {
        System.out.println("test2Hello3::Test Case One with Thread Id:- "
                + Thread.currentThread().getId());

        WebDriver driver = Utiles.getRemoteDriver("https://www.google.com/");
        assertTrue(driver.getTitle().contains("Google"));
        Utiles.takeScreenShot(driver, "test2Hello3");
        
        driver.quit();
    }

    @Test
    public void test2Hello4() {
        System.out.println("test2Hello4::Test Case One with Thread Id:- "
                + Thread.currentThread().getId());

        Utiles.print("test2Hello4 aqui estamos");

    }

}
