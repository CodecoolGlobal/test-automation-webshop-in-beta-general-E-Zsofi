package com.codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemComponent {

    private WebDriver driver;
    private WebElement itemElement;

    public ItemComponent(WebDriver driver, WebElement itemElement) {
        this.driver = driver;
        this.itemElement = itemElement;
    }

    public String getProductName() {
        return itemElement.findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductPrice() {
        return itemElement.findElement(By.className("inventory_item_price")).getText();
    }

    public void clickAddToCartButton() {
        WebElement addToCartButton = itemElement.findElement(By.tagName("button"));
        addToCartButton.click();
    }
}
