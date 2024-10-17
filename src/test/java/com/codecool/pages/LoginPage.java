package com.codecool.pages;

import com.codecool.base.BasePom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePom {

    @FindBy(id = "user-name")
    private WebElement usernameInputField;

    @FindBy(id = "password")
    private WebElement passwordInputField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver, new WebDriverWait(driver, Duration.ofSeconds(5)));
        PageFactory.initElements(driver, this);
    }

    private void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void login(String username, String password) {
        waitForElement(usernameInputField);
        usernameInputField.sendKeys(username);
        waitForElement(passwordInputField);
        passwordInputField.sendKeys(password);
        waitForElement(loginButton);
        loginButton.click();
    }

    public boolean checkLoginSuccess() {
        return driver.getPageSource().contains("Logout");
    }

    private void enterPassword(String password) {
        waitForElement(passwordInputField);
        passwordInputField.sendKeys(password);
    }

    private void enterUsername(String username) {
        waitForElement(usernameInputField);
        usernameInputField.sendKeys(username);
    }

    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return error.getText();
    }

    public void clickLoginButton() {
        waitForElement(loginButton);
        loginButton.click();
    }

    public void enterCredentials(String credentialType, String credential) {
        switch (credentialType) {
            case "password":
                enterPassword(credential);
                break;
            case "username":
                enterUsername(credential);
                break;
        }
    }
}
