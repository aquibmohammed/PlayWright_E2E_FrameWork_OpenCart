package com.ecommerce.automation.api;

import com.ecommerce.automation.dataproviders.LoginDataProvider;
import com.ecommerce.automation.dataproviders.LoginTestData;
import org.testng.annotations.Test;

public class apitest {

    @Test(dataProvider = "loginData",dataProviderClass = LoginDataProvider.class)
    public void LoginScenaros(LoginTestData data){

    }
}
