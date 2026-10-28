
package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.ProfileUpdateInterfaceElemets;
import com.utility.ExcelUtility;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileUpdate extends Base_Class implements ProfileUpdateInterfaceElemets {

    @FindBy(xpath = profileImage_xpath)
    public static WebElement profileImage;

    @FindBy(xpath = viewAndEdit_xpath)
    public static WebElement viewAndEdit;

    @FindBy(xpath = editProfile_xpath)
    public static WebElement editProfile;

    @FindBy(id = totalExperienceYear_id)
    public static WebElement totalExperienceYear;

    @FindBy(id = totalExperienceMonth_id)
    public static WebElement totalExperienceMonth;

    @FindBy(xpath = currentSalary_xpath)
    public static WebElement currentSalary;

    @FindBy(xpath = salaryBreakdown_xpath)
    public static WebElement salaryBreakdown;

    @FindBy(xpath = currentLocationCountry_xpath)
    public static WebElement currentLocationCountry;

    @FindBy(id = currentLocationCity_id)
    public static WebElement currentLocationCity;

    @FindBy(xpath = cityDropdownOption_xpath)
    public static WebElement cityDropdownOption;

    @FindBy(id = currentLocationVillage_id)
    public static WebElement currentLocationVillage;

    @FindBy(xpath = villageDropdownOption_xpath)
    public static WebElement villageDropdownOption;

    @FindBy(xpath = availability_xpath)
    public static WebElement availability;

    @FindBy(id = profileSave_id)
    public static WebElement profileSave;

    @FindBy(xpath = closePopup_xpath)
    public static WebElement closePopup;

    @FindBy(xpath = getProfileUpdateDate_xpath)
    public static WebElement profileUpdateDate;

    public ProfileUpdate() {
        PageFactory.initElements(driver, this);
    }

    public static void updateProfileNaukri() {
        waitForElementToBeClickable(profileImage, 5);
        elementClick(profileImage);
        waitForElementToBeClickable(viewAndEdit, 5);
        elementClick(viewAndEdit);
        waitForElementToBeClickable(editProfile, 5);
        elementClick(editProfile);
        String expYear = ExcelUtility.getCellValue("DATA", "ID", "Total_ExpYear");
        String expMonth = ExcelUtility.getCellValue("DATA", "ID", "Total_ExpMonth");
        waitForElementToBeClickable(totalExperienceYear, 5);
        elementClick(totalExperienceYear);
        passInput(totalExperienceYear, expYear);
        waitForElementToBeClickable(totalExperienceMonth, 5);
        elementClick(totalExperienceMonth);
        passInput(totalExperienceMonth, expMonth);
        String salary = ExcelUtility.getCellValue("DATA", "ID", "Salary");
        waitForElement(currentSalary, 5);
        passInput(currentSalary, Keys.CONTROL + "a");
        passInput(currentSalary, Keys.BACK_SPACE.toString());
        passInput(currentSalary, salary);
        String salaryBreakdownValue = ExcelUtility.getCellValue("DATA", "ID", "Salary_Breakdown");
        waitForElementToBeClickable(salaryBreakdown, 5);
        elementClick(salaryBreakdown);
        passInput(salaryBreakdown, salaryBreakdownValue);
        waitForElementToBeClickable(currentLocationCountry, 5);
        elementClick(currentLocationCountry);
        String city = ExcelUtility.getCellValue("DATA", "ID", "City");
        waitForElement(currentLocationCity, 5);
        currentLocationCity.clear();
        passInput(currentLocationCity, city);
        waitForElementToBeClickable(cityDropdownOption, 5);
        elementClick(cityDropdownOption);
        String village = ExcelUtility.getCellValue("DATA", "ID", "Village");
        waitForElement(currentLocationVillage, 5);
        elementClick(currentLocationVillage);
        passInput(currentLocationVillage, village);
        waitForElementToBeClickable(villageDropdownOption, 5);
        elementClick(villageDropdownOption);
        waitForElementToBeClickable(availability, 5);
        elementClick(availability);
        waitForElementToBeClickable(profileSave, 5);
        elementClick(profileSave);
        waitForElementToBeClickable(closePopup, 5);
        elementClick(closePopup);
        validateDate(profileUpdateDate);
    }
}
