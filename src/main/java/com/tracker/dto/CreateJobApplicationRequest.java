package com.tracker.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateJobApplicationRequest {
    @NotBlank
    private String applicantName;
    @NotBlank
    private String companyName;
    @NotBlank
    private String vacancyTitle;

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVacancyTitle() {
        return vacancyTitle;
    }

    public void setVacancyTitle(String vacancyTitle) {
        this.vacancyTitle = vacancyTitle;
    }
}
