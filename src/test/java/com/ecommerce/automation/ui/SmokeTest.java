package com.ecommerce.automation.ui;

import com.ecommerce.automation.base.baseTest;
import com.ecommerce.automation.config.ConfigManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends baseTest {
    @Test
    public void openTheApplicaton(){
        page.navigate(ConfigManager.getUIBaseURL());
        Assert.assertFalse(page.title().isBlank(),"Application page title should not be blank");
    }
}
