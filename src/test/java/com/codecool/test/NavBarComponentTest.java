package com.codecool.test;

import com.codecool.base.BaseTest;
import com.codecool.pages.NavBarComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NavBarComponentTest extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private NavBarComponent navBarComponent;


    @BeforeEach
    public void setUpProject() {
        setUp();
        navBarComponent = new NavBarComponent(driver);
    }

    @AfterEach
    public void tearDownProject() {
        tearDown();
    }

    @Test
    void allItemsListed(){
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnAllItemsLink();
        Assertions.assertEquals(SUT + "/inventory.html", driver.getCurrentUrl());
    }

    @Test
    void showAboutPage(){
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnAboutLink();
        Assertions.assertEquals("https://saucelabs.com/", driver.getCurrentUrl());
    }

    @Test
    void directToLogoutPage(){
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnLogoutLink();
        Assertions.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }

    @Test
    void resetAppClearsTheCart(){
        navBarComponent.addItem();
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnResetAppStateLink();
        boolean isEmpty = navBarComponent.isCartEmpty();
        Assertions.assertTrue(isEmpty);
    }
}
