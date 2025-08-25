package com.chitcheck.defaulters.guarantor_details;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class GuarantorDetails extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String suretyName;
    private String fatherOrHusbandName;
    private String mobileNo;
    private String aadharCardNo;
    private String address;
    private Integer age;
    private String occupation;
    private String emailId;
    private String pancard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_details_id")
    @JsonIgnore
    private PersonalDetails personalDetails;

    // Constructors
    public GuarantorDetails() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
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
    public PersonalDetails getPersonalDetails() { return personalDetails; }
    public void setPersonalDetails(PersonalDetails personalDetails) { this.personalDetails = personalDetails; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuarantorDetails that = (GuarantorDetails) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}