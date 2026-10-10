package com.ecommerce.automation.ui;

import com.ecommerce.automation.base.baseTest;
import com.ecommerce.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends baseTest {

    @Test(groups = {"ui", "smoke"})
    public void shouldLoginWithValidCredentials() {
        // Test implementation
    }

    @Test(groups ={"ui","regression"})
    public void shouldShowErrorForInvalidCredentials() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate();
        loginPage.login(
                "invalid@example.com",
                "InvalidPassword123!");

        Assert.assertTrue(
                loginPage.isErrorMessageVisible(),
                "Expected an error message for invalid credentials");
    }

}
