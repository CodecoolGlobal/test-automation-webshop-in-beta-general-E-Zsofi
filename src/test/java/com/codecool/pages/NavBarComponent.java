package com.codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavBarComponent {
    private By burgerButton = By.id("react-burger-menu-btn");
    private By allItemsLink = By.id("inventory_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By logoutLink = By.id("logout_sidebar_link");
    private By resetAppState = By.id("reset_sidebar_link");

    private WebDriver driver;
    private WebDriverWait wait;

    public NavBarComponent(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
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
}
