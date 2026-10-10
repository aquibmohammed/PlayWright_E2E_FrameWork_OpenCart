package com.ecommerce.automation.dataproviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDataReader {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules();

    public static <T> List<T> readList(String resourcePath, TypeReference<List<T>> typeReference){
        try(InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath)) {
            if(input == null){
                throw new IllegalArgumentException("Test data resources not found : "+resourcePath);
            }
            return OBJECT_MAPPER.readValue(input,typeReference);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to read test data: " + resourcePath, e);
        }
    }
}
