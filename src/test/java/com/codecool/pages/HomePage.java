package com.codecool.pages;

import com.codecool.base.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    public void sortProductsBy(String sortOptionValue) {
        WebElement sorter = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(sorter);
        select.selectByValue(sortOptionValue);  // Sorting by the given value
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(By.className("inventory_item_name")); // Return product names
    }

    public List<WebElement> getProductPrices() {
        return driver.findElements(By.className("inventory_item_price")); // Return product prices
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

}
