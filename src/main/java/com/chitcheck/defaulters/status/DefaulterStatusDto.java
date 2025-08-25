package com.chitcheck.defaulters.status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DefaulterStatusDto {

    private Integer id;

    @NotNull(message = "Personal Details ID is required to link the status")
    private Integer personalDetailsId;

    @NotBlank(message = "Status is required")
    private String status;

    private String statusDescription;

    // Constructors, Getters, and Setters
    public DefaulterStatusDto() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getPersonalDetailsId() { return personalDetailsId; }
    public void setPersonalDetailsId(Integer personalDetailsId) { this.personalDetailsId = personalDetailsId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getStatusDescription() { return statusDescription; }
    public void setStatusDescription(String statusDescription) { this.statusDescription = statusDescription; }
}