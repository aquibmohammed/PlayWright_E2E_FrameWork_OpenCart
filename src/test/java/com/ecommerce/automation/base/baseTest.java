package com.ecommerce.automation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ecommerce.automation.config.ConfigManager;
import com.ecommerce.automation.core.BrowserManager;
import com.ecommerce.automation.core.PlayWrightFactory;
import com.ecommerce.automation.pages.LoginPage;
import com.ecommerce.automation.utils.ExtentManager;
import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class baseTest {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected LoginPage loginPage;
    protected ExtentReports reports;
    protected ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void testSetup(Method method){
         reports = ExtentManager.getExtentInstance();
         test = reports.createTest(method.getName());
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
        if(iTestResult.isSuccess()){
            test.pass("TEST PASSED");
        }else if(!iTestResult.isSuccess()){
            test.fail("TEST FAILED");
        }else{
            test.skip("TEST SKIPPED");
        }
        reports.flush();
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
