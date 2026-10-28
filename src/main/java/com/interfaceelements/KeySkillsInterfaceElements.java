package com.interfaceelements;

public interface KeySkillsInterfaceElements {
    String existingSkills_xpath = "//div[@class='chipsContainer']/div[contains(@class,'chip')]";
    String keySkillEdit_xpath = "//span[@aria-label='Edit key skills']";
    String keySkillsSearch_id = "keySkillSugg";
    String keySkillsSave_id = "saveKeySkills";
    static String skillOption_xpath(String skill) {
        return "//div[contains(@class,'Sbtn') and normalize-space(.)='" + skill + "']";
    }
}