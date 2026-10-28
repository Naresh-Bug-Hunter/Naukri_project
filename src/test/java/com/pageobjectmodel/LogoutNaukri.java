package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.LogoutInterfaceElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutNaukri extends Base_Class implements LogoutInterfaceElements {

    @FindBy(xpath = profileImage_xpath)
    public WebElement profileImage;

    @FindBy(xpath = logoutButton_xpath)
    public WebElement logoutButton;

    public LogoutNaukri() {
        PageFactory.initElements(driver, this);
    }

    public void logoutNaukri(String expectedURL) {
        waitForElementToBeClickable(profileImage, 10);
        elementClick(profileImage);
        waitForElementToBeClickable(logoutButton, 10);
        elementClick(logoutButton);
        waitForUrl(expectedURL, 10);
        String actualURL = driver.getCurrentUrl();
        validationUrl(actualURL, expectedURL);
    }
}