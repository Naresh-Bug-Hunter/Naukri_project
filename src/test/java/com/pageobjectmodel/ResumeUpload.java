package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.ResumeUploadInterfaceElements;
import com.utility.FileReaderManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResumeUpload extends Base_Class implements ResumeUploadInterfaceElements {

    @FindBy(xpath = deleteExistingResume_xpath)
    public static WebElement deleteExistingResume;

    @FindBy(xpath = confirmDeleteResume_xpath)
    public static WebElement confirmDeleteResume;

    @FindBy(xpath = resumeUpload_xpath)
    public static WebElement resumeUpload;

    @FindBy(xpath = uploadedResumeName_xpath)
    public static WebElement uploadedResumeName;

    public ResumeUpload(){
        PageFactory.initElements(driver, this);
    }

    public static void uploadResumeNaukri(){
        elementClick(deleteExistingResume);
        waitForElement(confirmDeleteResume,5);
        elementClick(confirmDeleteResume);
        jsScroll(150);
        waitForElement(resumeUpload,10);
        String filepath = FileReaderManager.getProperty("resume");
        elementClick(resumeUpload);
        fileUpload(filepath);
        waitForElement(uploadedResumeName, 10);
        validation(uploadedResumeName, "Naresh_K_QA_Resume.pdf");
    }
}