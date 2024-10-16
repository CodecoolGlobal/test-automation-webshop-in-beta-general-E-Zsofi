package com.codecool.base;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BaseTest {
        private final String SUT = "https://www.saucedemo.com/";
        protected WebDriver driver;
        protected WebDriverWait wait;

        public void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(SUT);
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
}
