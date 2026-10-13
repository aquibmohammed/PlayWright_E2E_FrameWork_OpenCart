package com.ecommerce.automation.factory;

import com.ecommerce.automation.pages.LoginPage;
import com.microsoft.playwright.Page;

public class PageObjectFactory {
    private PageObjectFactory() {}
    public static LoginPage loginPage() { return new LoginPage(DriverFactory.page()); }
    public static HomePage homePage() { return new HomePage(DriverFactory.page()); }
    public static CartPage cartPage() { return new CartPage(DriverFactory.page()); }
}

}
