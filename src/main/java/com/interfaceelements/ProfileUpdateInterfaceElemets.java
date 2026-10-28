package com.interfaceelements;

public interface ProfileUpdateInterfaceElemets {
    String profileImage_xpath = "//img[@alt='naukri user profile image']";
    String viewAndEdit_xpath = "//a[text()='View & Update Profile']";
    String editProfile_xpath = "//em[text()='editOneTheme']";
    String totalExperienceYear_id = "exp-years-droopeFor";
    String totalExperienceMonth_id = "exp-months-droopeFor";
    String currentSalary_xpath = "//input[@aria-label='Current salary']";
    String salaryBreakdown_xpath = "//input[@id='salaryBreakDownDDFor']";
    String currentLocationCountry_xpath = "//label[@for='location-country-india']";
    String currentLocationCity_id = "locationSugg";
    String cityDropdownOption_xpath = "//div[contains(@class,'Sbtn') and contains(.,'Chennai')]";
    String currentLocationVillage_id = "locality-droopeFor";
    String villageDropdownOption_xpath = "//a[starts-with(@data-id,'locality-droope_')]";
    String availability_xpath = "//span[@data-id='1']";
    String profileSave_id = "saveBasicDetailsBtn";
    String closePopup_xpath = "(//span[@aria-label='Close'])[7]";
    String getProfileUpdateDate_xpath = "//span[@class='typ-14Medium mod-date-val']";
}