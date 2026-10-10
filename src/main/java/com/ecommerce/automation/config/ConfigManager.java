package com.ecommerce.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties PROPERTIES = new Properties();
    static{
        loadproperties();
    }

    private ConfigManager(){
        // prevent initilization
    }

    private static void loadproperties(){
        String enviornment = System.getProperty("env");
        if (enviornment == null || enviornment.isBlank()) {
            enviornment = "qa";
        }
        enviornment = enviornment.trim().toLowerCase();
        String fileName= "config/"+ enviornment + ".properties";
        try{
            InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(fileName);
            if(inputStream == null){
                throw new IllegalStateException("Configuration file not found: "+ fileName);
            }
            PROPERTIES.load(inputStream);
        }catch (IOException e){
                throw new IllegalStateException("Failed to load Configration: "+fileName);
        }
    }

    public static String get(String key){
        String value= System.getProperty(key);
        if (value == null || value.isBlank()) {
            value = PROPERTIES.getProperty(key);
        }
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("Missing configuration property: "+key);
        }
        value = PROPERTIES.getProperty(key);
        return value.trim();
    }

    public static String getUIBaseURL(){
        return get("ui.baseUrl");
    }
    public static String getAPIBaseURL(){
        return get("api.baseUrl");
    }
    public static String getBrowser(){
        return get("browser").trim().toLowerCase();
    }
    public static boolean getHeadless(){
        return Boolean.parseBoolean(get("headless").trim().toLowerCase());
    }
    public static double getActionTimeOut(){
        return Double.parseDouble(get("action.timeout.ms"));
    }
    public static double getNavigationTimeOut(){
        return Double.parseDouble(get("navigation.timeout.ms"));
    }

    public static int getPositiveInt(String key){
        try{
            int value = Integer.parseInt(key);
            if(value <=0){
                throw new IllegalArgumentException("configuration must be positive: "+key);
            }
            return  value;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Configuration must be a valid integer: "+ key ,e);
        }

    }

}
