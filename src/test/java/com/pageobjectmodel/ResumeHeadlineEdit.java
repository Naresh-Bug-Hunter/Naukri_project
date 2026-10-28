package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.ResumeHeadLineInterfaceElements;
import com.utility.ExcelUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResumeHeadlineEdit extends Base_Class implements ResumeHeadLineInterfaceElements {

    @FindBy(xpath = resumeHeadline_xpath)
    public static WebElement resumeHeadline;

    @FindBy(xpath = textArea_xpath)
    public static WebElement textArea;

    @FindBy(xpath = saveButton_xpath)
    public static WebElement saveButton;

    @FindBy(xpath = updatedResumeHeadline_xpath)
    public static WebElement updatedResumeHeadline;

    public ResumeHeadlineEdit() {
        PageFactory.initElements(driver, this);
    }

    public static void resumeHeadlineNaukri() throws InterruptedException {
        waitForElementToBeClickable(resumeHeadline, 5);
        elementClick(resumeHeadline);
        String headline = ExcelUtility.getCellValue("DATA", "ID", "Resume_Headline");
        waitForElement(textArea, 5);
        if (textArea.getAttribute("value").isEmpty()) {
            passInput(textArea, headline);
        } else {
            textArea.clear();
            passInput(textArea, headline);
        }
        waitForElementToBeClickable(saveButton, 10);
        elementClick(saveButton);
        waitForElementInvisibility(textArea, 5);
        String expectedHeadline = ExcelUtility.getCellValue("DATA", "ID", "Resume_Headline");
        waitForElement(updatedResumeHeadline, 10);
        validation(updatedResumeHeadline, expectedHeadline);
    }
}
