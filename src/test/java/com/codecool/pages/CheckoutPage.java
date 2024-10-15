package com.codecool.pages;

import com.codecool.base.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePom {
    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, new WebDriverWait(driver, Duration.ofSeconds(5)));
    }

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By checkOutButton = By.id("continue");
    private final By cancelButton = By.id("back-to-products");
    private final By error = By.className("error-button");
    private final By price = By.cssSelector("[data-test='inventory-item-price']");
    private final By totalCalculation = By.cssSelector(".summary_subtotal_label");
    private final By finishButton = By.id("finish");
    
    
    
    public void fillTheFields(final String firstName, final String lastName, final String postalCode) {
        WebElement firstNameFieldInput =  wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
       firstNameFieldInput.sendKeys(firstName);
       
       WebElement lastNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
       lastNameInput.sendKeys(lastName);
       
       WebElement postalCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField));
       postalCodeInput.sendKeys(postalCode);
    }
    
    public void clickContinueButton() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(checkOutButton));
        continueButton.click();
    }
    
    public void clickCancelButton() {
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancel.click();
    }
    
    public Boolean thereIsAnError () {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        return errorMessage.isDisplayed();
    }
    
    public Boolean isTheRightPrice() {
        List<WebElement> priceElements = driver.findElements(price);
        double totalPrice = priceElements.stream()
                .mapToDouble(element -> Double.parseDouble(element.getText().replace("$", ""))) 
                .sum();
        WebElement sum = wait.until(ExpectedConditions.visibilityOfElementLocated(totalCalculation));
        double total = Double.parseDouble(sum.getText().replace("$", ""));
        return totalPrice == total;
    }
    
    public void finishButtonClick(){
        WebElement finish = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finish.click();
    }
}
