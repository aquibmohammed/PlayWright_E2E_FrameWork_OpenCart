package com.ecommerce.automation.api;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryApiTest extends BaseAPITest{

    @Test(groups = {"api","smoke"})
    public void getCategoriesShouldReturnSuccess(){
        APIResponse response = apiClient.get("categories");
        Assert.assertEquals(response.status(),200,"Categories API should return HTTP 200");
        String responseBody = response.text();
        Assert.assertFalse(responseBody.isBlank(),"Categories API response body should not be empty");
        System.out.println(
                "Categories API status: " + response.status()
        );
        System.out.println("Categories API response received successfully.");

    }
}
