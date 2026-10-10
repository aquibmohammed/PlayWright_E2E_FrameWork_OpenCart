package com.ecommerce.automation.pages;

import com.microsoft.playwright.Page;

public abstract class BasePage {
    protected Page page;

    public BasePage(Page page) {
        if(page ==null){
            throw new IllegalArgumentException("Page must not be null");
        }
        this.page = page;
    }
    public String getPageTitle(){
        return page.title();
    }
    public String getCurrentUrl(){
        return  page.url();
    }

}
