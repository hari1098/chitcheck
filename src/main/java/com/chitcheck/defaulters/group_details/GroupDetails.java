package com.chitcheck.defaulters.group_details;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class GroupDetails extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String groupNo;
    private String psoNumber;
    private String agreementNo;
    private LocalDate agreementDate;
    private BigDecimal installment;
    private BigDecimal chitValue;
    private LocalDate firstAuction;
    private LocalDate lastAuction;
    private BigDecimal amountPaid;
    private BigDecimal amountOutstanding;
    private String branchName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_details_id")
    @JsonIgnore
    private PersonalDetails personalDetails;

    // Constructors
    public GroupDetails() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getGroupNo() { return groupNo; }
    public void setGroupNo(String groupNo) { this.groupNo = groupNo; }
    public String getPsoNumber() { return psoNumber; }
    public void setPsoNumber(String psoNumber) { this.psoNumber = psoNumber; }
    public String getAgreementNo() { return agreementNo; }
    public void setAgreementNo(String agreementNo) { this.agreementNo = agreementNo; }
    public LocalDate getAgreementDate() { return agreementDate; }
    public void setAgreementDate(LocalDate agreementDate) { this.agreementDate = agreementDate; }
    public BigDecimal getInstallment() { return installment; }
    public void setInstallment(BigDecimal installment) { this.installment = installment; }
    public BigDecimal getChitValue() { return chitValue; }
    public void setChitValue(BigDecimal chitValue) { this.chitValue = chitValue; }
    public LocalDate getFirstAuction() { return firstAuction; }
    public void setFirstAuction(LocalDate firstAuction) { this.firstAuction = firstAuction; }
    public LocalDate getLastAuction() { return lastAuction; }
    public void setLastAuction(LocalDate lastAuction) { this.lastAuction = lastAuction; }
    public BigDecimal getAmountPaid() { return amountPaid; }
    public void setAmountPaid(BigDecimal amountPaid) { this.amountPaid = amountPaid; }
    public BigDecimal getAmountOutstanding() { return amountOutstanding; }
    public void setAmountOutstanding(BigDecimal amountOutstanding) { this.amountOutstanding = amountOutstanding; }
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
    public PersonalDetails getPersonalDetails() { return personalDetails; }
    public void setPersonalDetails(PersonalDetails personalDetails) { this.personalDetails = personalDetails; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDetails that = (GroupDetails) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}