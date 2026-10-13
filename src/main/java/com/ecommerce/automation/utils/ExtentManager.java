package com.ecommerce.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
    private  static  ExtentReports extent;

    public static  ExtentReports getExtentInstance(){
        if(extent == null) {
            ExtentHtmlReporter extentHtmlReporter =
                    new ExtentHtmlReporter("src/test/resources/test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(extentHtmlReporter);
        }
        return extent;
    }
}
