package com.chitcheck.myProfile.companyProfile.model;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.auth.model.User;
import com.chitcheck.myProfile.directordetails.model.Director;
import com.chitcheck.myProfile.proofdetails.ProofDetails; // <-- CORRECTED IMPORT
import com.chitcheck.myProfile.status.StatusUpdate;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class CompanyProfile extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String companyName;
    private String contactNo;
    private String email;
    private String address;
    private String contactPerson;
    private String pancard;
    private String cin;
    private String gstNumber;
    private String profileImagePath;
    private String pancardImagePath;
    private String cinImagePath;
    private String gstImagePath;

    @OneToMany(mappedBy = "companyProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Director> directors;

    @OneToOne(mappedBy = "companyProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProofDetails proofDetails;

    @OneToMany(mappedBy = "companyProfile", cascade = CascadeType.ALL)
    private List<StatusUpdate> statusUpdates;

    // Constructors
    public CompanyProfile() { }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getPancard() { return pancard; }
    public void setPancard(String pancard) { this.pancard = pancard; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }
    public String getProfileImagePath() { return profileImagePath; }
    public void setProfileImagePath(String profileImagePath) { this.profileImagePath = profileImagePath; }
    public String getPancardImagePath() { return pancardImagePath; }
    public void setPancardImagePath(String pancardImagePath) { this.pancardImagePath = pancardImagePath; }
    public String getCinImagePath() { return cinImagePath; }
    public void setCinImagePath(String cinImagePath) { this.cinImagePath = cinImagePath; }
    public String getGstImagePath() { return gstImagePath; }
    public void setGstImagePath(String gstImagePath) { this.gstImagePath = gstImagePath; }
    public List<Director> getDirectors() { return directors; }
    public void setDirectors(List<Director> directors) { this.directors = directors; }
    public ProofDetails getProofDetails() { return proofDetails; }
    public void setProofDetails(ProofDetails proofDetails) { this.proofDetails = proofDetails; }
    public List<StatusUpdate> getStatusUpdates() { return statusUpdates; }
    public void setStatusUpdates(List<StatusUpdate> statusUpdates) { this.statusUpdates = statusUpdates; }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyProfile that = (CompanyProfile) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}