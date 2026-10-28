package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.LoginPageInterfaceElements;
import com.pageobjectmanager.PageObjectManager;
import com.utility.ExcelUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Base_Class implements LoginPageInterfaceElements {

    @FindBy(id = loginOption_id)
    public static WebElement loginOption;

    @FindBy(xpath = emailID_xpath)
    public static WebElement emailInput;

    @FindBy(xpath = password_xpath)
    public static WebElement passwordInput;

    @FindBy(xpath = loginButton_xpath)
    public static WebElement loginButton;

    @FindBy(xpath = usernameTitle_xpath)
    public static WebElement username;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public static void loginNaukri() {
        elementClick(loginOption);
        passInput(emailInput, PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("email"));
        passInput(passwordInput, PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("password"));
        waitForElement(loginButton, 5);
        elementClick(loginButton);
        waitForElement(username, 10);
        String expectedUsername = ExcelUtility.getCellValue("DATA", "ID", "Title");
        validation(username, expectedUsername);
    }
}