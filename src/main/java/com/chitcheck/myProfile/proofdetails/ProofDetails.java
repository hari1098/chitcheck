package com.chitcheck.myProfile.proofdetails;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.myProfile.companyProfile.model.CompanyProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class ProofDetails extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String gstCertificatePath;
    private String pancardPath;
    private String cinPath;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_profile_id", unique = true)
    @JsonIgnore
    private CompanyProfile companyProfile;

    public ProofDetails() { }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getGstCertificatePath() { return gstCertificatePath; }
    public void setGstCertificatePath(String gstCertificatePath) { this.gstCertificatePath = gstCertificatePath; }
    public String getPancardPath() { return pancardPath; }
    public void setPancardPath(String pancardPath) { this.pancardPath = pancardPath; }
    public String getCinPath() { return cinPath; }
    public void setCinPath(String cinPath) { this.cinPath = cinPath; }
    public CompanyProfile getCompanyProfile() { return companyProfile; }
    public void setCompanyProfile(CompanyProfile companyProfile) { this.companyProfile = companyProfile; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProofDetails that = (ProofDetails) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}