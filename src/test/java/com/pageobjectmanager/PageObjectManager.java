package com.pageobjectmanager;

import com.pageobjectmodel.*;
import com.utility.FileReaderManager;

public class PageObjectManager {

    private FileReaderManager fileReaderManager;
    private static PageObjectManager pageObjectManager;
    private LoginPage loginPage;
    private ProfileUpdate updateProfile;
    private ResumeUpload resumeUpload;
    private ResumeHeadlineEdit resumeHeadline;
    private KeySkillsUpdate keySkillsUpdate;
    private LogoutNaukri logoutNaukri;

    public KeySkillsUpdate getKeySkillsUpdate() {
        if (keySkillsUpdate == null) {
            keySkillsUpdate = new KeySkillsUpdate();}
        return keySkillsUpdate;
    }

    public LogoutNaukri getLogoutNaukri() {
        if (logoutNaukri == null) {
            logoutNaukri = new LogoutNaukri();
        }
        return logoutNaukri;
    }

    public FileReaderManager getFileReaderManager() {
        if (fileReaderManager == null) {
            fileReaderManager = new FileReaderManager();
        }
        return fileReaderManager;
    }

    public static PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager();
        }
        return pageObjectManager;
    }

    public LoginPage getLoginPage(){
        if(loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public ResumeUpload getResumeUpload() {
        if(resumeUpload == null){
            resumeUpload = new ResumeUpload();
        }
        return resumeUpload;
    }

    public ProfileUpdate getUpdateProfile() {
        if(updateProfile == null){
            updateProfile = new ProfileUpdate();
        }
        return updateProfile;
    }

    public ResumeHeadlineEdit getResumeHeadline() {
        if (resumeHeadline == null) {
            resumeHeadline = new ResumeHeadlineEdit();
        }
        return resumeHeadline;
    }
}