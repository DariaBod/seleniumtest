package org.dariabodiakova.pages;

import org.dariabodiakova.utils.AppConfig;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {

    private String pageUrl = AppConfig.getPageUrl("home");



    public MainPage(WebDriver driver) {
        super(driver);
        driver.get(pageUrl);
    }
}
