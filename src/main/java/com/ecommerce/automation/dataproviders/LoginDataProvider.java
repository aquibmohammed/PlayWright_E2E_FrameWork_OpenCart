package com.ecommerce.automation.dataproviders;

import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Type;
import java.util.List;

public class LoginDataProvider {

    public static Object[][] loginData(){
        List<LoginTestData> data = TestDataReader.readList(
                "testdata/login.json", new TypeReference<List<LoginTestData>>() {});
         return data.stream().map(item -> new Object[]{item}).toArray(Object[][]::new);

    }
}
