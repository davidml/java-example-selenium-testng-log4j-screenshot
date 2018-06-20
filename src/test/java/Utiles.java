
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

/**
 *
 * @author David
 */
public class Utiles {

    private static org.apache.log4j.Logger Log = null;
    private static final String FILE_PATH_SCREENSHOT = "c:/MOP/target/screenshots/";
    private static final String IP_DOCKER_SELENIUM = "http://192.168.99.100:4444/wd/hub";
//    private static final String IP_DOCKER_SELENIUM = "http://184.72.126.252:4444/wd/hub";
    public static HashMap<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    public static WebDriver getLocalDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"test-type"});
//        options.addArguments("headless");
        return new ChromeDriver(options);
    }

    public static WebDriver getLocalDriver(String url) {
        return getDriverToURL(getLocalDriver(), url);
    }

    public static WebDriver getRemoteDriver(String url) {
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            // docker pull selenium/standalone-chrome, ip es la asignada por docker
            WebDriver driver = new RemoteWebDriver(new URL(IP_DOCKER_SELENIUM), capabilities);
            return getDriverToURL(driver, url);

        } catch (MalformedURLException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static WebDriver getRemoteDriver() {
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            // docker pull selenium/standalone-chrome, ip es la asignada por docker
            return new RemoteWebDriver(new URL(IP_DOCKER_SELENIUM), capabilities);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void takeScreenShot(WebDriver driver, String testName) {

        if (driver == null) {
            print("Utiles::takeScreenShot::ERROR:: No se puede grabar el screenshot del test: " + testName);
            return;
        }

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = FILE_PATH_SCREENSHOT + testName + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(fileName));
            print("Utiles::takeScreenShot::OK:: screenshot guardada en:: " + fileName);
        } catch (IOException e) {
            print("Utiles::takeScreenShot::ERROR::printStackTrace:: No se puede grabar el screenshot del test: " + testName);
            e.printStackTrace();
        }
    }

    public static void print(String txt) {

        if (Log == null) {
            Log = org.apache.log4j.Logger.getLogger(Log.class.getName());
            DOMConfigurator.configure("log4j.xml");
        }

        Log.info(txt);
        Reporter.log(txt);
    }

    public static WebDriver addWebDriver(String testName) {

        Utiles.print(testName);
//        WebDriver driver = getRemoteDriver();
        WebDriver driver = getLocalDriver();
        drivers.put(testName, driver);
        return driver;
    }

    public static WebDriver getDriverDetails(String testName) {

        print("Utiles::getDriverDetails::testName : " + testName);
        WebDriver driver = drivers.get(testName);
        if (driver != null) {
            print("Utiles::getDriverDetails:: drivers.size() : " + drivers.size());
            return driver;
        }

        return null;
    }

    static WebDriver getDriverToURL(WebDriver driver, String url) {

        driver.get(url);

        // Wait for the page to load, timeout after 15 seconds
        Boolean exito = (new WebDriverWait(driver, 15)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        });

        if (exito) {
            return driver;
        }

        return null;
    }

    public static void quitWebDriver(String testName) {

        print("Utiles::quitWebDriver::testName : " + testName);
        WebDriver driver = drivers.get(testName);
        if (driver != null) {
            driver.quit();
            print("Utiles::quitWebDriver:: before drivers.size() : " + drivers.size());
            drivers.remove(testName);
            print("Utiles::quitWebDriver:: after drivers.size() : " + drivers.size());
        }

    }

}
