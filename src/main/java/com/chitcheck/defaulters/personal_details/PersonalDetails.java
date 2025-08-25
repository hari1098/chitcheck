package com.chitcheck.defaulters.personal_details;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.defaulters.bonds_details.BondDetails;
import com.chitcheck.defaulters.case_details.CaseDetails;
import com.chitcheck.defaulters.group_details.GroupDetails;
import com.chitcheck.defaulters.guarantor_details.GuarantorDetails;
import com.chitcheck.defaulters.status.DefaulterStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class PersonalDetails extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subscriberName;
    private String contactPerson;
    private String area;
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNo;
    private String email;
    private String introducer;
    private String nominee;
    private String relationship;
    private String panCard;
    private String subscriberType;
    private String aadharCard;
    private String gstNumber;
    private String profileImagePath;

    @OneToMany(mappedBy = "personalDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupDetails> groupDetails;

    @OneToMany(mappedBy = "personalDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuarantorDetails> guarantorDetails;

    @OneToMany(mappedBy = "personalDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BondDetails> bondDetails;

    @OneToMany(mappedBy = "personalDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaseDetails> caseDetails;

    @OneToOne(mappedBy = "personalDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private DefaulterStatus defaulterStatus;

    // Constructors
    public PersonalDetails() {
    }

    // Getters and Setters
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
    public List<GroupDetails> getGroupDetails() { return groupDetails; }
    public void setGroupDetails(List<GroupDetails> groupDetails) { this.groupDetails = groupDetails; }
    public List<GuarantorDetails> getGuarantorDetails() { return guarantorDetails; }
    public void setGuarantorDetails(List<GuarantorDetails> guarantorDetails) { this.guarantorDetails = guarantorDetails; }
    public List<BondDetails> getBondDetails() { return bondDetails; }
    public void setBondDetails(List<BondDetails> bondDetails) { this.bondDetails = bondDetails; }
    public List<CaseDetails> getCaseDetails() { return caseDetails; }
    public void setCaseDetails(List<CaseDetails> caseDetails) { this.caseDetails = caseDetails; }
    public DefaulterStatus getDefaulterStatus() { return defaulterStatus; }
    public void setDefaulterStatus(DefaulterStatus defaulterStatus) { this.defaulterStatus = defaulterStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalDetails that = (PersonalDetails) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}