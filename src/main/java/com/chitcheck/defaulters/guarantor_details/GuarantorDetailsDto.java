package com.chitcheck.defaulters.guarantor_details;

import jakarta.validation.constraints.*;

public class GuarantorDetailsDto {

    private Integer id;

    @NotNull(message = "Personal Details ID is required to link the guarantor")
    private Integer personalDetailsId;

    @NotBlank(message = "Name of the Surety is required")
    private String suretyName;

    private String fatherOrHusbandName;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String mobileNo;

    @Pattern(regexp = "^\\d{12}$", message = "Aadhar Card must be 12 digits")
    private String aadharCardNo;

    private String address;

    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    private String occupation;

    @Email(message = "Invalid email format")
    private String emailId;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN card format")
    private String pancard;

    // Constructors, Getters, and Setters
    public GuarantorDetailsDto() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getPersonalDetailsId() { return personalDetailsId; }
    public void setPersonalDetailsId(Integer personalDetailsId) { this.personalDetailsId = personalDetailsId; }
    public String getSuretyName() { return suretyName; }
    public void setSuretyName(String suretyName) { this.suretyName = suretyName; }
    public String getFatherOrHusbandName() { return fatherOrHusbandName; }
    public void setFatherOrHusbandName(String fatherOrHusbandName) { this.fatherOrHusbandName = fatherOrHusbandName; }
    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }
    public String getAadharCardNo() { return aadharCardNo; }
    public void setAadharCardNo(String aadharCardNo) { this.aadharCardNo = aadharCardNo; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
    public String getPancard() { return pancard; }
    public void setPancard(String pancard) { this.pancard = pancard; }
}