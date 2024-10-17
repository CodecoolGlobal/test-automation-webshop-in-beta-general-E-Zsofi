package com.codecool.test;

import com.codecool.base.BaseTest;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        setUp();
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void teardown() {
        tearDown();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login.csv")
    @DisplayName("Test login feature with standard_user's proper and wrong credentials")
    void testLogin(String username, String password, boolean expectedResult) {
        loginPage.login(username, password);
        boolean loginSuccess = loginPage.checkLoginSuccess();
        assertEquals(expectedResult, loginSuccess);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login-missing-credentials.csv")
    @DisplayName("Test login feature with one credential missing at the time")
    void testMissingCredential(String credentialType, String credential, String expectedMessage) {
        loginPage.enterCredentials(credentialType, credential);
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test login feature by leaving the username and password input fields empty")
    void testEmptyUsernameAndPassword() {
        loginPage.clickLoginButton();
        boolean loginSuccess = loginPage.checkLoginSuccess();
        assertFalse(loginSuccess);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/urls.csv")
    @DisplayName("Test navigating from login page to other pages from the login page")
    void testNavigatingFromLoginPage(String url) {
        driver.get(url);
        boolean loginSuccess = loginPage.checkLoginSuccess();
        assertFalse(loginSuccess);
    }
}
