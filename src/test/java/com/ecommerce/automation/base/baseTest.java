package com.ecommerce.automation.base;

import com.ecommerce.automation.config.ConfigManager;
import com.ecommerce.automation.core.BrowserManager;
import com.ecommerce.automation.core.PlayWrightFactory;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class baseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeMethod(alwaysRun = true)
    public void testSetup(){
        playwright = PlayWrightFactory.createPlayWright();
        try{
            browser = BrowserManager.createBrowser(playwright);
            browserContext = browser.newContext();
            browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true)
                    .setSnapshots(true).setSources(true));
            page = browserContext.newPage();
            page.setDefaultTimeout(ConfigManager.getActionTimeOut());
            page.setDefaultNavigationTimeout(ConfigManager.getNavigationTimeOut());
        }catch (RuntimeException | Error e){
            throw e;
        }
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult){
    try {

        if (browser != null) {
            browser.close();
        }

        if (browserContext != null) {
            if (iTestResult != null && !iTestResult.isSuccess()) {
                String testName = iTestResult.getMethod().getMethodName() + "_" + System.currentTimeMillis();
                Path screenShotDir = Paths.get("target", "test-result", "screenshots");
                Path traceDir = Paths.get("target", "test-results", "traces");

                Files.createDirectories(screenShotDir);
                Files.createDirectories(traceDir);

                if (page != null) {
                    page.screenshot(
                            new Page.ScreenshotOptions()
                                    .setPath(screenShotDir.resolve(testName + ".png")).setFullPage(true));
                }else{
                    browserContext.tracing().stop();
                }
            }
        }
    } catch (Exception e) {
        System.out.println( "Failed to capture test diagnostics: " + e.getMessage());
    }finally {
     if(browserContext != null) {
         browserContext.close();
     }
     if (page != null) {
            page.close();
     }
     if (playwright != null) {
            playwright.close();
        }
    }
    }
}
