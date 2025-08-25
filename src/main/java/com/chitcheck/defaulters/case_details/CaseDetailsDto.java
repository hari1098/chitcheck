package com.chitcheck.defaulters.case_details;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CaseDetailsDto {

    private Integer id;

    @NotNull(message = "Personal Details ID is required to link the case")
    private Integer personalDetailsId;

    @NotBlank(message = "Case Number is required")
    private String caseNumber;

    private String currentStatus;
    private String description;
    private String registerOffice;

    @FutureOrPresent(message = "Next Hearing Date must be in the present or future")
    private LocalDate nextHearingDate;

    // Constructors, Getters, and Setters
    public CaseDetailsDto() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getPersonalDetailsId() { return personalDetailsId; }
    public void setPersonalDetailsId(Integer personalDetailsId) { this.personalDetailsId = personalDetailsId; }
    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }
    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRegisterOffice() { return registerOffice; }
    public void setRegisterOffice(String registerOffice) { this.registerOffice = registerOffice; }
    public LocalDate getNextHearingDate() { return nextHearingDate; }
    public void setNextHearingDate(LocalDate nextHearingDate) { this.nextHearingDate = nextHearingDate; }
}