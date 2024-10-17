package com.codecool.test;

import com.codecool.base.BasePom;
import com.codecool.base.BaseTest;
import com.codecool.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPageTest extends BaseTest {
    private LoginPage loginPage;
    private CartPage cartPage;
    private HomePage homePage;

    @BeforeEach
    public void setupProject(){
        setUp();
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver, wait);
        homePage = new HomePage(driver, wait);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testClickOnCartDirectsToTheCartPage(){
        homePage.clickOnCartIcon();
        Assertions.assertEquals(SUT + "cart.html", driver.getCurrentUrl());
    }

    @Test
    public void testItemsListedOnThePage(){
        ItemComponent backpack = homePage.getProductByName("Sauce Labs Backpack");
        backpack.clickAddToCartButton();
        String itemName = backpack.getProductName();
        homePage.clickOnCartIcon();
        boolean isContain = cartPage.getProductNamesInTheCart().contains(itemName);
        Assertions.assertTrue(isContain);
    }

    @Test
    public void testClickOnContinueShoppingDirectsToHome(){
        homePage.clickOnCartIcon();
        cartPage.clickOnContinueShopping();
        Assertions.assertEquals(SUT + "inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void testRemoveOneElementFromTheCart(){
        ItemComponent backpack = homePage.getProductByName("Sauce Labs Backpack");
        backpack.clickAddToCartButton();
        homePage.clickOnCartIcon();
        cartPage.removeItemFromCart();
        int itemsInTheCart = cartPage.getProductNamesInTheCart().size();
        Assertions.assertEquals(itemsInTheCart, 0);
    }

    @Test
    public void testCheckoutButtonDirectsToCheckoutPage(){
        homePage.clickOnCartIcon();
        cartPage.clickOnCheckout();
        Assertions.assertEquals(SUT + "checkout-step-one.html", driver.getCurrentUrl());
    }




}
