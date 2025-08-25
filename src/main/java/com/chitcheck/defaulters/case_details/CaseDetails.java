package com.chitcheck.defaulters.case_details;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class CaseDetails extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String caseNumber;
    private String currentStatus;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String registerOffice;
    private LocalDate nextHearingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_details_id")
    @JsonIgnore
    private PersonalDetails personalDetails;

    // Constructors
    public CaseDetails() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
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
    public PersonalDetails getPersonalDetails() { return personalDetails; }
    public void setPersonalDetails(PersonalDetails personalDetails) { this.personalDetails = personalDetails; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseDetails that = (CaseDetails) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}