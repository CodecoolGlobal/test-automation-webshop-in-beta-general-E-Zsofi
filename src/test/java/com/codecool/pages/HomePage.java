package com.codecool.pages;

import com.codecool.base.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePom {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By cartIcon = By.id("shopping_cart_container");

    public List<ItemComponent> getProducts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));
        List<ItemComponent> products = new ArrayList<>();
        List<WebElement> productElements = driver.findElements(By.className("inventory_item"));
        for (WebElement productElement : productElements) {
            products.add(new ItemComponent(driver, productElement));
        }
        return products;
    }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));
        List<String> products = new ArrayList<>();
        List<WebElement> productElements = driver.findElements(By.className("inventory_item"));
        for (WebElement productElement : productElements) {
            products.add(new ItemComponent(driver, productElement).getProductName());
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

    public boolean isCartEmpty() {
        try {
            WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

public void clickOnCartIcon(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).click();
}


    public String checkProductPrice(ItemComponent product) {
        return product.getProductPrice();
    }
}
