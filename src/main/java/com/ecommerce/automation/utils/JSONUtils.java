package com.ecommerce.automation.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    public static String toJson(Object object){
        try{
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    "Unable to serialize object to JSON", e);
        }
    }
    public static <T> T fromJson(String json,Class<T> type){
        try{
            return MAPPER.readValue(json,type);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(
                    "Unable to deserialize JSON into "
                            + type.getSimpleName(), e);
        }
    }
}
