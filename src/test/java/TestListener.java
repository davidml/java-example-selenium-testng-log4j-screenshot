
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Utiles.takeScreenShot(Utiles.getDriverDetails(result.getName()), result.getName());
        Utiles.quitWebDriver(result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        Utiles.print("TestListener::onTestStart:: se agrega instancia de webdriver. getName " + result.getName());
//        Utiles.print("TestListener::onTestStart:: se agrega instancia de webdriver. getMethod  " + result.getMethod());
        Utiles.addWebDriver(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Utiles.quitWebDriver(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
