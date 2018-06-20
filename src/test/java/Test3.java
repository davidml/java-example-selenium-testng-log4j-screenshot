
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author David
 */
public class Test3 {

    @Test
    public void testOpenGoogle() {

        String nameTest = new Object() {
        }.getClass().getEnclosingMethod().getName();

        Utiles.print("Test3::testOpenGoogle:: se obtiene instancia por el nombre: " + nameTest);
        WebDriver driver = Utiles.getDriverDetails(nameTest);

        Utiles.print("Test3::testOpenGoogle:: abrir el browser en la url ");
        driver = Utiles.getDriverToURL(driver, "http://www.google.cl");

        Utiles.print("Test3::testOpenGoogle:: verifica titulo del browser");
        assertTrue(driver.getTitle().contains("Google"));
//        Assert.fail("Test was failed");

    }

    @Test
    public void testOpenYahoo() {

        String nameTest = new Object() {
        }.getClass().getEnclosingMethod().getName();

        Utiles.print("Test3::testOpenYahoo:: se obtiene instancia por el nombre: " + nameTest);
        WebDriver driver = Utiles.getDriverDetails(nameTest);

        Utiles.print("Test3::testOpenYahoo:: abrir el browser en la url ");
        Utiles.getDriverToURL(driver, "https://www.yahoo.com");

        Utiles.print("Test3::testOpenGoogle:: verifica titulo del browser");
        assertTrue(driver.getTitle().contains("Yahoo"));
//        Assert.fail("Test was failed");

    }

    @Test
    public void testOpenAmazon() {

        String nameTest = new Object() {
        }.getClass().getEnclosingMethod().getName();

        Utiles.print("Test3::testOpenAmazon:: se obtiene instancia por el nombre: " + nameTest);
        WebDriver driver = Utiles.getDriverDetails(nameTest);

        Utiles.print("Test3::testOpenAmazon:: abrir el browser en la url ");
        Utiles.getDriverToURL(driver, "https://www.amazon.com");

        Utiles.print("Test3::testOpenAmazon:: verifica titulo del browser");
        assertTrue(driver.getTitle().contains("Amazon"));
//        Assert.fail("Test was failed");

    }

}
