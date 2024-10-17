package com.codecool.test;

import com.codecool.base.BaseTest;
import com.codecool.pages.HomePage;
import com.codecool.pages.LoginPage;
import com.codecool.pages.NavBarComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class NavBarComponentTest extends BaseTest {
    private NavBarComponent navBarComponent;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeEach
    public void setUpProject() {
        setUp();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver, wait);
        loginPage.login("standard_user", "secret_sauce");
        navBarComponent = new NavBarComponent(driver);
    }

    @AfterEach
    public void tearDownProject() {
        tearDown();
    }

    @Test
    public void testAllItemsListed(){
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnAllItemsLink();
        Assertions.assertEquals(SUT + "inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void testShowAboutPage(){
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnAboutLink();
        Assertions.assertEquals("https://saucelabs.com/", driver.getCurrentUrl());
    }

    @Test
    public void testDirectToLogoutPage(){
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnLogoutLink();
        Assertions.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }

    @Test
    public void testResetAppClearsTheCart(){
        navBarComponent.addItem();
        navBarComponent.clickOnBurgerButton();
        navBarComponent.clickOnResetAppStateLink();
        boolean result = homePage.isCartEmpty();
        Assertions.assertTrue(result);
    }
}
