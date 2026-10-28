package com.listener;

import com.aventstack.extentreports.ExtentTest;
import com.base.Base_Class;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class ExtentReport_Test {

    public static ExtentTest extentTest;

    @BeforeSuite
    public void extentTestReportStartup(){
        Base_Class base = new Base_Class() {
        };
        base.extentsReportStart(null);
    }

    @AfterSuite
    public void extentTestReportEnd() throws IOException {
        Base_Class base = new Base_Class() {
        };
        base.extentsReportTeardown(null);
    }
}
