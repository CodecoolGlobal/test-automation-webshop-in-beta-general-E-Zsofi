package com.codecool.test;

import com.codecool.base.BaseTest;
import com.codecool.pages.CheckoutPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPageTest extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;

    CheckoutPage checkoutPage;
    
    @BeforeEach
    public void setUpProject() {
        setUp();
        checkoutPage = new CheckoutPage(driver,wait);
    }

    @AfterEach
    public void tearDownProject() {
        tearDown();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/checkOut.csv", numLinesToSkip = 1)
    public void AddItemToCart(String firstName, String lastName, String postalCode, boolean expectedResult) {
        checkoutPage.fillTheFields(firstName, lastName, postalCode);
        boolean actualResult = checkoutPage.thereIsAnError();
        Assertions.assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void cancelTheFirstStepOfCheckout() {
        checkoutPage.clickCancelButton();
        String actualResult = driver.getCurrentUrl();
        String expectedResult = "https://www.saucedemo.com/cart.html";
        Assertions.assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void firstStepOfCheckoutDone() {
        checkoutPage.fillTheFields("John", "Doe", "0987654321");
        checkoutPage.clickContinueButton();
        boolean actualResult = checkoutPage.isTheRightPrice();
        Assertions.assertTrue(actualResult);
    }
    
    @Test
    public void cancelSecondStepOfCheckout() {
        checkoutPage.fillTheFields("John", "Doe", "0987654321");
        checkoutPage.clickContinueButton();
        checkoutPage.clickCancelButton();
        String actualResult = driver.getCurrentUrl();
        String expectedResult = "https://www.saucedemo.com/inventory.html";
        Assertions.assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void secondStepOfCheckoutDone() {
        checkoutPage.fillTheFields("John", "Doe", "0987654321");
        checkoutPage.clickContinueButton();
        checkoutPage.finishButtonClick();
        checkoutPage.clickBackToButton();
        String actualResult = driver.getCurrentUrl();
        String expectedResult = "https://www.saucedemo.com/inventory.html";
        Assertions.assertEquals(expectedResult, actualResult);
    }
}