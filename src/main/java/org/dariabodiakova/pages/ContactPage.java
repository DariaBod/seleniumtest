package org.dariabodiakova.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactPage extends AbstractPage {

    @FindBy(tagName = "h3")
    private WebElement header;

    @FindBy(tagName = "h3")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "subject")
    private WebElement subjectDropdown;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(id = "attachment")
    private WebElement attachmentUploadForm;

    @FindBy(className = "btnSubmit")
    private WebElement button;

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void fillContactForm(String firstName, String lastName, String email, String subject, String message) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> header.isDisplayed());
        enterText(firstNameField, firstName);
        enterText(lastNameField, lastName);
        enterText(emailField, email);
        selectDropdownByVisibleText(subjectDropdown, subject);
        enterText(messageField, message);
    }

    public void submitContactForm() {
        button.click();
    }
}