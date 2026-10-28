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
        Base_Class.extentsReportStart(System.getProperty("user.dir") + "/Reports/index.html");
    }

    @AfterSuite
    public void extentTestReportEnd() throws IOException {
        Base_Class.extentsReportTeardown(System.getProperty("user.dir") + "/Reports/index.html");
    }
}
