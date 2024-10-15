package com.codecool.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemComponent {

    private WebDriver driver;
    private WebElement productElement;
    private WebDriverWait wait;

    public ItemComponent(WebDriver driver, WebElement productElement) {
        this.driver = driver;
        this.productElement = productElement;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 másodperces várakozás
    }

    public String getProductName() {
        // Várakozás az elem láthatóságára
        wait.until(ExpectedConditions.visibilityOf(productElement.findElement(By.className("inventory_item_name"))));
        return productElement.findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOf(productElement.findElement(By.className("inventory_item_price"))));
        return productElement.findElement(By.className("inventory_item_price")).getText();
    }

    public void addToCart() {
        WebElement addToCartButton = productElement.findElement(By.cssSelector(".btn_inventory"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)); // Várakozás, hogy kattintható legyen
        addToCartButton.click();
    }

    public void removeFromCart() {
        WebElement removeFromCartButton = productElement.findElement(By.cssSelector(".btn_secondary"));
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButton)); // Várakozás, hogy kattintható legyen
        removeFromCartButton.click();
    }
}

