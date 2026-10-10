package com.ecommerce.automation.core;

import com.ecommerce.automation.config.ConfigManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserManager {
    public static Browser createBrowser(Playwright playWright){
        String browserName = ConfigManager.getBrowser();
        boolean headless = ConfigManager.getHeadless();
        switch (browserName){
            case "chromium":
                return playWright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
            case "firefox":
                return playWright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
            case "webkit":
                return playWright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
            default:
                throw new IllegalArgumentException ("unsupported browser: "+browserName);
            }
        }
    }

