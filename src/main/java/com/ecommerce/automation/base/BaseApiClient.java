package com.ecommerce.automation.api;

import com.ecommerce.automation.config.ConfigManager;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import java.util.Map;

public class ApiClient implements AutoCloseable {

    private final APIRequestContext apiRequestContext;

    public ApiClient(Playwright playwright) {
        this.apiRequestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL(ConfigManager.getAPIBaseURL())
                        .setTimeout(30_000)
                        .setExtraHTTPHeaders(Map.of("Accept","application/json",
                                "X-Oc-Restadmin-Id",
                                ConfigManager.getAPIKey()))
        );
    }

    public APIResponse get(String endpoint){
        return apiRequestContext.get(endpoint);
    }

    public APIResponse post(String endpoint,String jsonBody){
        return apiRequestContext.post(endpoint, RequestOptions.create()
                .setHeader("Content-Type","application/json")
                .setData(jsonBody));
    }

    public APIResponse put(String endpoint,String jsonBody){
        return apiRequestContext.put(endpoint,RequestOptions.create()
                .setHeader("Content-Type","application/json")
                .setData(jsonBody));
    }

    public APIResponse delete(String endpoint){
        return apiRequestContext.delete(endpoint);
    }

    @Override
    public void close() throws Exception {
            apiRequestContext.dispose();
    }
}
