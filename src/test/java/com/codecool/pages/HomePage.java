package com.codecool.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 másodperces várakozás
    }

    public List<ItemComponent> getProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item"))); // Várakozás, hogy az első termék megjelenjen
        List<ItemComponent> products = new ArrayList<>();
        List<WebElement> productElements = driver.findElements(By.className("inventory_item"));
        for (WebElement productElement : productElements) {
            products.add(new ItemComponent(driver, productElement));
        }
        return products;
    }

    public ItemComponent getProductByName(String productName) {
        for (ItemComponent product : getProducts()) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}

