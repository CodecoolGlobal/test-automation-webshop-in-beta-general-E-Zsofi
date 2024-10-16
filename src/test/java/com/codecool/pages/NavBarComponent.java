package com.codecool.pages;

import com.codecool.base.BasePom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class NavBarComponent extends BasePom {
    private By burgerButton = By.id("react-burger-menu-btn");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppState = By.id("reset_sidebar_link");
    private By cartButton = By.id("shopping_cart_container");
    private By cartHasItemPic = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    private By addItemToCart = By.id("add-to-cart-sauce-labs-backpack");

    public NavBarComponent(WebDriver driver) {
        super(driver, new WebDriverWait(driver, Duration.ofSeconds(5)));
    }

    public void clickOnBurgerButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(burgerButton)).click();
    }

    public void clickOnAllItemsLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(allItemsLink)).click();
    }

    public void clickOnAboutLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(aboutLink)).click();
    }

    public void clickOnLogoutLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).click();
    }

    public void clickOnResetAppStateLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetAppState)).click();
    }

    public void redirectToCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartButton)).click();
    }

    public void addItem(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(addItemToCart)).click();
    }

    public boolean isCartEmpty(){
        try {
            WebElement cartItem = driver.findElement(cartHasItemPic);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

}
