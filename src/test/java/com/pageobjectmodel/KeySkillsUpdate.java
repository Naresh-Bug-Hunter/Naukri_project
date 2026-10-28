package com.pageobjectmodel;

import com.base.Base_Class;
import com.interfaceelements.KeySkillsInterfaceElements;
import com.interfaceelements.ResumeHeadLineInterfaceElements;
import com.utility.ExcelUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KeySkillsUpdate extends Base_Class
        implements KeySkillsInterfaceElements,
        ResumeHeadLineInterfaceElements {

    @FindBy(xpath = keySkillEdit_xpath)
    public WebElement keySkilledit;

    @FindBy(id = keySkillsSearch_id)
    public WebElement keySkillsSearch;

    @FindBy(id = keySkillsSave_id)
    public WebElement saveButton;

    public KeySkillsUpdate() {
        PageFactory.initElements(driver, this);
    }

    public void keySkillUpdate() {

        String skills = ExcelUtility.getCellValue("DATA", "ID", "Key_Skills");

        if (skills == null || skills.trim().isEmpty()) {
            System.out.println("No skills found in Excel.");
            return;
        }

        jsScrollToElement(keySkilledit);
        waitForElementToBeClickable(keySkilledit, 15);
        jsClick(keySkilledit);

        waitForElement(keySkillsSearch, 15);

        By existingSkillsLocator = By.xpath(existingSkills_xpath);
        List<WebElement> existingSkills = driver.findElements(existingSkillsLocator);

        int existingCount = existingSkills.size();
        for (String skillValue : skills.split(",")) {
            String skill = skillValue.trim();
            if (skill.isEmpty()) {
                continue;
            }
            if (existingCount >= 20) {
                System.out.println("Maximum of 20 skills reached. Skipping: " + skill);
                break;
            }

            boolean alreadyExists = false;
            for (WebElement existingSkill : existingSkills) {
                String existingName = getAttribute(existingSkill, "title");
                if (existingName == null || existingName.isBlank()) {
                    existingName = getText(existingSkill);
                }
                if (existingName != null && existingName.trim().equalsIgnoreCase(skill)) {
                    alreadyExists = true;
                    break;
                }
            }

            if (alreadyExists) {
                continue;
            }
            waitForElement(keySkillsSearch, 15);
            keySkillsSearch.clear();
            passInput(keySkillsSearch, skill);

            By skillOption = By.xpath(KeySkillsInterfaceElements.skillOption_xpath(skill));
            WebElement option = waitForElementToBeClickable(skillOption, 15);
            elementClick(option);

            int previousCount = existingCount;
            waitForElementCountToIncrease(existingSkillsLocator, previousCount, 15);
            existingSkills = driver.findElements(existingSkillsLocator);
            existingCount = existingSkills.size();
        }

        jsScrollToElement(saveButton);
        waitForElementToBeClickable(saveButton, 15);
        elementClick(saveButton);
    }
}