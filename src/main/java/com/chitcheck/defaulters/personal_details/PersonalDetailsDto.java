package com.chitcheck.defaulters.personal_details;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PersonalDetailsDto {

    private Integer id;

    @NotBlank(message = "Subscriber Name is required")
    private String subscriberName;

    private String contactPerson;
    private String area;

    @Past(message = "Date of Birth must be in the past")
    private LocalDate dateOfBirth;

    private String address;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phoneNo;

    @Email(message = "Invalid email format")
    private String email;

    private String introducer;
    private String nominee;
    private String relationship;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN card format")
    private String panCard;

    private String subscriberType;

    @Pattern(regexp = "^\\d{12}$", message = "Aadhar card must be 12 digits")
    private String aadharCard;

    @Pattern(regexp = "^\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z][A-Z\\d]{1}$", message = "Invalid GSTIN format")
    private String gstNumber;

    private String profileImagePath;

    public PersonalDetailsDto() { }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getSubscriberName() { return subscriberName; }
    public void setSubscriberName(String subscriberName) { this.subscriberName = subscriberName; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getIntroducer() { return introducer; }
    public void setIntroducer(String introducer) { this.introducer = introducer; }
    public String getNominee() { return nominee; }
    public void setNominee(String nominee) { this.nominee = nominee; }
    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }
    public String getPanCard() { return panCard; }
    public void setPanCard(String panCard) { this.panCard = panCard; }
    public String getSubscriberType() { return subscriberType; }
    public void setSubscriberType(String subscriberType) { this.subscriberType = subscriberType; }
    public String getAadharCard() { return aadharCard; }
    public void setAadharCard(String aadharCard) { this.aadharCard = aadharCard; }
    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }
    public String getProfileImagePath() { return profileImagePath; }
    public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }
}