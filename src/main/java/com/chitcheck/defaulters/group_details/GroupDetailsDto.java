package com.chitcheck.defaulters.group_details;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GroupDetailsDto {

    private Integer id;

    @NotNull(message = "Personal Details ID is required to link this group")
    private Integer personalDetailsId;

    @NotBlank(message = "Group No is required")
    private String groupNo;

    private String psoNumber;
    private String agreementNo;
    private LocalDate agreementDate;

    @Positive(message = "Installment must be a positive number")
    private BigDecimal installment;

    @Positive(message = "Chit Value must be a positive number")
    private BigDecimal chitValue;

    private LocalDate firstAuction;
    private LocalDate lastAuction;
    private BigDecimal amountPaid;
    private BigDecimal amountOutstanding;
    private String branchName;

    // Constructors, Getters, and Setters
    public GroupDetailsDto() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getPersonalDetailsId() { return personalDetailsId; }
    public void setPersonalDetailsId(Integer personalDetailsId) { this.personalDetailsId = personalDetailsId; }
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
}