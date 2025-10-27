package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.dariabodiakova.api.Authorization;
import org.dariabodiakova.models.Users;
import org.dariabodiakova.utils.AppConfig;
import org.dariabodiakova.utils.TestConfig;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.UUID;

public class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<Cookie> authCookie = new ThreadLocal<>();

    @Parameters({"browser", "auth"})
    @BeforeClass
    public void setup(@Optional("chrome") String browser, @Optional("false") String auth) {
        driver.set(createDriver(browser));
        RestAssured.baseURI = AppConfig.getBaseApiUrl();
        if (Boolean.parseBoolean(auth)) {
            performApiLogin();
            injectCookies();
        }
    }

    private WebDriver createDriver(String browser) {
        boolean headless = TestConfig.isHeadless();

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0 Safari/537.36");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("--headless=new");
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private void performApiLogin() {
        Response response = new Authorization().login(Users.regular());
        String sessionCookie = response.getCookie("access_token");
        if (sessionCookie != null) {
            authCookie.set(new Cookie("session_id", sessionCookie));
        }
    }

    private void injectCookies() {
        WebDriver currentDriver = driver.get();
        Cookie cookie = authCookie.get();
        if (cookie != null) {
            currentDriver.get("https://practicesoftwaretesting.com");
            currentDriver.manage().addCookie(cookie);
            currentDriver.navigate().refresh();
        }
    }


    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterClass
    public void teardown() {
        if(driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
