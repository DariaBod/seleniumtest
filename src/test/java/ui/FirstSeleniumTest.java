package ui;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.dariabodiakova.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class FirstSeleniumTest extends BaseTest {

    @Test(description = "Сheck page title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test verifies that page works")
    public void checkPageTitle() {
        MainPage mainPage = new MainPage(driver.get());
        mainPage.takeScreenshot();
        assertEquals("Practice Software Testing - Toolshop - v5.0", mainPage.getPageTitle());
    }
}
