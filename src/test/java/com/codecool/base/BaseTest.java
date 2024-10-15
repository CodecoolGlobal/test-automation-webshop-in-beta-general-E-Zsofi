package com.codecool.base;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


    public abstract class BaseTest {
        private final String SUT = "https://www.saucedemo.com/";
        protected WebDriver driver;

        public void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(SUT);
        }

        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
}
