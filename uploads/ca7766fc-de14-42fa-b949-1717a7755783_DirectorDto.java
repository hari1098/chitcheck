package com.chitcheck.myProfile.directordetails.dto;

import com.chitcheck.myProfile.directordetails.model.DirectorStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;


public class DirectorDto {
    private Integer id;

    @NotBlank(message = "Director name cannot be blank")
    private String name;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN card format")
    private String pancard;

    @Pattern(regexp = "^\\d{12}$", message = "Aadhar card must be 12 digits")
    private String aadharCard;

    private String directorType;
    private LocalDate joinDate;

    @Email(message = "Please provide a valid email address")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String mobileNo;

    private String address;
    private DirectorStatus status;

    // Constructors
    public DirectorDto() {
    }

    public DirectorDto(Integer id, String name, String pancard, String aadharCard, String directorType, LocalDate joinDate, String email, String mobileNo, String address, DirectorStatus status) {
        this.id = id;
        this.name = name;
        this.pancard = pancard;
        this.aadharCard = aadharCard;
        this.directorType = directorType;
        this.joinDate = joinDate;
        this.email = email;
        this.mobileNo = mobileNo;
        this.address = address;
        this.status = status;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPancard() { return pancard; }
    public void setPancard(String pancard) { this.pancard = pancard; }
    public String getAadharCard() { return aadharCard; }
    public void setAadharCard(String aadharCard) { this.aadharCard = aadharCard; }
    public String getDirectorType() { return directorType; }
    public void setDirectorType(String directorType) { this.directorType = directorType; }
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public DirectorStatus getStatus() { return status; }
    public void setStatus(DirectorStatus status) { this.status = status; }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorDto that = (DirectorDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(pancard, that.pancard) && Objects.equals(aadharCard, that.aadharCard) && Objects.equals(directorType, that.directorType) && Objects.equals(joinDate, that.joinDate) && Objects.equals(email, that.email) && Objects.equals(mobileNo, that.mobileNo) && Objects.equals(address, that.address) && status == that.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, pancard, aadharCard, directorType, joinDate, email, mobileNo, address, status);
    }
}