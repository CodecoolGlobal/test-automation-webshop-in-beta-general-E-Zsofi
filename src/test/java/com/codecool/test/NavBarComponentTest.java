package com.codecool.test;

import com.codecool.base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavBarComponentTest extends BaseTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUpProject() {
        setUp();
    }

    @AfterEach
    public void tearDownProject() {
        tearDown();
    }


}
