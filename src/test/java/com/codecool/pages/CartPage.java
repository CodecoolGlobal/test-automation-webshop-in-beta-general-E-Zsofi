package com.codecool.pages;

import com.codecool.base.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CartPage extends BasePom {
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");
    private final By removeItem = By.id("remove-sauce-labs-backpack");


    public List<String> getProductNamesInTheCart() {
        try {
            List<String> products = new ArrayList<>();
            List<WebElement> productElements = driver.findElements(By.className("cart_item"));
            for (WebElement productElement : productElements) {
                products.add(new ItemComponent(driver, productElement).getProductName());
            }
            return products;
        } catch (NoSuchElementException e) {
            return new ArrayList<String>();
        }

    }

    public void clickOnContinueShopping(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton)).click();
    }

    public void clickOnCheckout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton)).click();
    }

    public void removeItemFromCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeItem)).click();
    }


}
