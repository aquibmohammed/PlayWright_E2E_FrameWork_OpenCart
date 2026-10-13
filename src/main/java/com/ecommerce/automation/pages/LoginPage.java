package com.ecommerce.automation.pages;

import com.ecommerce.automation.config.ConfigManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends com.ecommerce.automation.pages.BasePage {
    private final Locator emailInput ;
    private final Locator passwordInput;
    private final Locator signINButton;
    private final Locator SingUPLink;
    private final Locator errorMessage;

    public LoginPage(Page page,String baseUrl) {
        super(page,baseUrl);

        emailInput = page.getByPlaceholder("Enter Email");
        passwordInput = page.getByPlaceholder("Enter Password");

        signINButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Sign in"));
        SingUPLink = page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("New user? Signup"));
        errorMessage = page.locator(".errorMessage");
    }

    public void navigateToLoginPage() {
        navigateTo("/login");
    }

    public void login(String email, String password) {
        fill(emailInput,email);
        fill(passwordInput,password);
        click(signINButton);

    }

    public boolean isErrorMessageVisible() {
        return isVisible(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}


