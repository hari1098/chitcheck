package com.chitcheck.defaulters.bonds_details;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class BondDetails extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bondType;
    private String bondNumber;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_details_id")
    @JsonIgnore
    private PersonalDetails personalDetails;

    // Constructors
    public BondDetails() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getBondType() { return bondType; }
    public void setBondType(String bondType) { this.bondType = bondType; }
    public String getBondNumber() { return bondNumber; }
    public void setBondNumber(String bondNumber) { this.bondNumber = bondNumber; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public PersonalDetails getPersonalDetails() { return personalDetails; }
    public void setPersonalDetails(PersonalDetails personalDetails) { this.personalDetails = personalDetails; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BondDetails that = (BondDetails) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}