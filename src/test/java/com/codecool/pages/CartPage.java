package com.codecool.pages;

import com.codecool.base.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePom {
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private final By shoppingCartIcon = By.id("shopping_cart_container");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");


    public void clickOnShoppingCartIcon(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartIcon)).click();
    }

    public List<ItemComponent> getProductsInTheCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));
        List<ItemComponent> products = new ArrayList<>();
        List<WebElement> productElements = driver.findElements(By.className("inventory_item"));
        for (WebElement productElement : productElements) {
            products.add(new ItemComponent(driver, productElement));
        }
        return products;
    }

    public void continueShopping(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton)).click();
    }


}
