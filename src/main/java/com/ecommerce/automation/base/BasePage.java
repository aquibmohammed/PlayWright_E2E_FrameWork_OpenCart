package com.ecommerce.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.Objects;

public abstract class BasePage {
    protected final Page page;
    protected final String baseUrl;

    public BasePage(Page page,String baseUrl) {
        if(page ==null){
            throw new IllegalArgumentException("Page must not be null");
        }
        if(baseUrl == null || baseUrl.isEmpty()){
            throw new IllegalArgumentException("baseUrl must not be empty");
        }
        this.page = page;
        this.baseUrl = baseUrl;
    }

    public void navigateTo(String path){
            if(path == null || path.isBlank()){
                throw new IllegalArgumentException("Path must not be blank");
            }
            String normalizedPath = path.startsWith("/")?path:"/"+path;
            page.navigate(baseUrl+normalizedPath);
    }
    // Navigation

    public void navigateForward(){
        page.goForward();
    }
    public void navigateBack(){
        page.goBack();
    }
    public void refreshPage(){
        page.reload();
    }

    // Waiting
    public void waitForPageLoad(){
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
    public void waitForVisible(Locator locator){
        Objects.requireNonNull(locator,"Locator must not be null")
                .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    // Common element interactions
    public void click(Locator locator){
        Objects.requireNonNull(locator,"Locator must not be null").click();
    }
    public void fill(Locator locator, String value){
        Objects.requireNonNull(locator,"Locator must not be null");
        Objects.requireNonNull(value,"Locator must not be null");
               locator.fill(value);
    }

    // Element state

    public boolean isVisible(Locator locator) {
        return Objects.requireNonNull(
                locator, "Locator must not be null"
        ).isVisible();
    }

    public boolean isEnabled(Locator locator) {
        return Objects.requireNonNull(
                locator, "Locator must not be null"
        ).isEnabled();
    }

    // Element information

    public String getText(Locator locator) {
        return Objects.requireNonNull(
                locator, "Locator must not be null"
        ).innerText().trim();
    }

    public String getInputValue(Locator locator) {
        return Objects.requireNonNull(
                locator, "Locator must not be null"
        ).inputValue();
    }

    // Browser information

    public String getCurrentUrl() {
        return page.url();
    }

    public String getPageTitle() {
        return page.title();
    }

    public Page getPage() {
        return page;
    }

}
