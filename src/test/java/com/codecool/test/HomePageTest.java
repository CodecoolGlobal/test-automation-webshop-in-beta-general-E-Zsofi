package com.codecool.test;

import com.codecool.base.BaseTest;
import com.codecool.pages.HomePage;
import com.codecool.pages.ItemComponent;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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
        product.clickRemoveFromCartButton();
        assertTrue(homePage.isCartEmpty(), "The cart should be empty");
    }

    @Test
    public void testEmptyCart() {
        assertTrue(homePage.isCartEmpty());
    }

    @Test
    public void testSortByNameZA() {
        homePage.sortProductsBy("za");
        List<String> productNames = homePage.getProductNames()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> sortedNames = productNames.stream()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());
        assertEquals(sortedNames, productNames, "Products are not sorted Z-A");
    }

    @Test
    public void testSortByNameAZ() {
        homePage.sortProductsBy("az");
        List<String> productNames = homePage.getProductNames()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> sortedNames = productNames.stream()
                .sorted()
                .collect(Collectors.toList());
        assertEquals(sortedNames, productNames, "Products are not sorted A-Z");
    }

    @Test
    public void testSortByPriceLowToHigh() {
        homePage.sortProductsBy("lohi");
        List<Double> productPrices = homePage.getProductPrices()
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = productPrices.stream()
                .sorted()
                .collect(Collectors.toList());
        assertEquals(sortedPrices, productPrices, "Products are not sorted by price low to high");
    }

    @Test
    public void testSortByPriceHighToLow() {
        homePage.sortProductsBy("hilo");
        List<Double> productPrices = homePage.getProductPrices()
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sortedPrices = productPrices.stream()
                .sorted((a, b) -> b.compareTo(a))  // Reverse order
                .collect(Collectors.toList());
        assertEquals(sortedPrices, productPrices, "Products are not sorted by price high to low");
    }


}
