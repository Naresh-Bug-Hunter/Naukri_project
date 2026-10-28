package com.interfaceelements;

public interface ResumeUploadInterfaceElements {
    String deleteExistingResume_xpath = "//span[@data-title='delete-resume']";
    String confirmDeleteResume_xpath = "(//button[text()='Delete'])[3]";
    String resumeUpload_xpath = "//span[text()='Upload resume']";
    String uploadedResumeName_xpath = "//div[@title='Naresh_K_QA_Resume.pdf']";
}