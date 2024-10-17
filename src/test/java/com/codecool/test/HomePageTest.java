package com.codecool.test;

import com.codecool.base.BaseTest;
import com.codecool.pages.HomePage;
import com.codecool.pages.ItemComponent;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest extends BaseTest {

    private HomePage homePage;
    private WebDriverWait wait;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver, wait);
        loginPage.login("standard_user", "secret_sauce");
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void testProductsAreDisplayed() {
        List<ItemComponent> products = homePage.getProducts();
        assertFalse(products.isEmpty(), "The product list should not be empty");
    }

    @Test
    public void testGetProductByName() {
        ItemComponent product = homePage.getProductByName("Sauce Labs Backpack");
        assertNotNull(product, "The product should be found by name");
        assertEquals("Sauce Labs Backpack", product.getProductName(), "Product name should match");
    }

    @Test
    public void testProductNotFound() {
        ItemComponent product = homePage.getProductByName("Non-existent Product");
        assertNull(product, "The product should not be found");
    }

    @Test
    public void testAddProduct() {
        ItemComponent product = homePage.getProductByName("Sauce Labs Backpack");
        product.clickAddToCartButton();
        boolean isCartEmpty = homePage.isCartEmpty();
        assertFalse(isCartEmpty, "The cart shouldn't be empty");
    }

    @Test
    public void testRemoveProduct() {
        ItemComponent product = homePage.getProductByName("Sauce Labs Backpack");
        product.clickAddToCartButton();

    }

    @Test
    public void testEmptyCart() {
        assertTrue(homePage.isCartEmpty());
    }

}
