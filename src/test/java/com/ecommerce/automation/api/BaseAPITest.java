package com.ecommerce.automation.api;

import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseAPITest {
    protected Playwright playwright;
    protected ApiClient apiClient;

    @BeforeMethod(alwaysRun = true)
    public void setupApi(){
        try {
            playwright = Playwright.create();
            apiClient = new ApiClient(playwright);
        }catch (RuntimeException e){
            playwright.close();
            playwright = null;
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownApi(){

            if(apiClient != null){
                try {
                    apiClient.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if(playwright != null){
                playwright.close();
            }
            apiClient = null;
            playwright = null;

    }
}
