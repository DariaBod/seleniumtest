package listener;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui.BaseTest;

import java.io.ByteArrayInputStream;

public class AllureScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getWebDriverInstance();
        if (driver != null) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(result.getName() + " - Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshotBytes), "png");
        }
    }

    private WebDriver getWebDriverInstance() {
        return BaseTest.getDriver();
    }
}
