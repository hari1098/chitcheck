package com.chitcheck.defaulters.bonds_details;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BondDetailsDto {

    private Integer id;

    @NotNull(message = "Personal Details ID is required to link the bond")
    private Integer personalDetailsId;

    @NotBlank(message = "Bond Type is required")
    private String bondType;

    @NotBlank(message = "Bond Number is required")
    private String bondNumber;

    private String filePath;

    // Constructors, Getters, and Setters
    public BondDetailsDto() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getPersonalDetailsId() { return personalDetailsId; }
    public void setPersonalDetailsId(Integer personalDetailsId) { this.personalDetailsId = personalDetailsId; }
    public String getBondType() { return bondType; }
    public void setBondType(String bondType) { this.bondType = bondType; }
    public String getBondNumber() { return bondNumber; }
    public void setBondNumber(String bondNumber) { this.bondNumber = bondNumber; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}