package com.chitcheck.defaulters.status;

import com.chitcheck.auth.model.Auditable;
import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class DefaulterStatus extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String statusDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_details_id", unique = true)
    @JsonIgnore
    private PersonalDetails personalDetails;

    // Constructors
    public DefaulterStatus() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatusDescription() { return statusDescription; }
    public void setStatusDescription(String statusDescription) { this.statusDescription = statusDescription; }
    public PersonalDetails getPersonalDetails() { return personalDetails; }
    public void setPersonalDetails(PersonalDetails personalDetails) { this.personalDetails = personalDetails; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaulterStatus that = (DefaulterStatus) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}