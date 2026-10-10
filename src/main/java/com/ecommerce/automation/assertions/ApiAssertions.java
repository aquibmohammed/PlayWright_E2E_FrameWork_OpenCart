package com.ecommerce.automation.assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;

public class ApiAssertions {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void assertStatus(APIResponse apiResponse, int expectedStatus){
        Assert.assertEquals(apiResponse.status(),expectedStatus,"Unexpected HTTP status");
    }
    public static  void assertJSONFieldExists(APIResponse response,String fieldName){
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(response.text());
            Assert.assertTrue(jsonNode.has(fieldName),"Expected JSON Field: "+fieldName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
