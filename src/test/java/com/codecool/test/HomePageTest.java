package com.codecool.test;

import com.codecool.pages.HomePage;
import com.codecool.pages.ItemComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        homePage = new HomePage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
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


}

