package com.ecommerce.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage{
    private final Locator emailInput ;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        super(page);

        emailInput = page.getByLabel("E-Mail Address");
        passwordInput = page.getByLabel("Password");

        loginButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login"));

        errorMessage = page.locator(".alert-danger");
    }

    public void navigate() {
        page.navigate("/index.php?route=account/login");
    }

    public void login(String email, String password) {
        emailInput.fill(email);
        passwordInput.fill(password);
        loginButton.click();
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.isVisible();
    }

    public String getErrorMessage() {
        return errorMessage.innerText();
    }
}


