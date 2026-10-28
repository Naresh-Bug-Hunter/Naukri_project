package com.runner;

import com.base.Base_Class;
import com.listener.ExtentReport_Test;
import com.listener.ITestListnerClass;
import com.pageobjectmanager.PageObjectManager;
import com.utility.FileReaderManager;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(ITestListnerClass.class)
public class NaukriRunner extends Base_Class {

    @BeforeSuite
    public void extentsReportStart(){
        extentsReportStart(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("start"));
    }

    @BeforeClass
    public void propertySetup(){
        launchBrowser(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("browser"));
        launchUrl(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("url"));
    }

    @Test(priority = 1)
    public void validLogin(){
        ExtentReport_Test.extentTest=extentsReports.createTest("Naukri Test" + " : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Naukri Web Application");
        PageObjectManager.getPageObjectManager().getLoginPage().loginNaukri();
    }

    @Test(priority = 2,dependsOnMethods = "validLogin")
    public void updateProfile(){
        PageObjectManager.getPageObjectManager().getUpdateProfile().updateProfileNaukri();
    }

    @Test(priority = 3,dependsOnMethods = "validLogin")
    public void resumeUpload(){
        PageObjectManager.getPageObjectManager().getResumeUpload().uploadResumeNaukri();
    }

    @Test(priority = 4,dependsOnMethods = "validLogin")
    public void resumeHeadline() throws InterruptedException {
        PageObjectManager.getPageObjectManager().getResumeHeadline().resumeHeadlineNaukri();
    }

    @Test(priority = 5,dependsOnMethods = "validLogin")
    public void keySkillUpdate(){
        PageObjectManager.getPageObjectManager().getKeySkillsUpdate().keySkillUpdate();
    }

    @Test(priority = 6,dependsOnMethods = "validLogin")
    public void logoutNaukri(){
        String url = FileReaderManager.getProperty("url");
        PageObjectManager.getPageObjectManager().getLogoutNaukri().logoutNaukri(url);
    }

    @AfterClass
    public void sessionTermination(){
        browserTerminate();
    }

    @AfterSuite
    public void extentsReportTeardown() throws IOException {
        extentsReportTeardown(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("end"));
    }
}
