package com.chitcheck.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;

public class RegisterRequest {
    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @Email(message = "Please provide a valid email address")
    @NotBlank
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String mobileNo;

    @NotBlank
    private String username;

    @NotBlank
    private String password;


    public RegisterRequest() {
    }

    public RegisterRequest(String companyName, String email, String mobileNo, String username, String password) {
        this.companyName = companyName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.username = username;
        this.password = password;
    }


    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyName(String companyName)
    { this.companyName = companyName;
    }
    public String getEmail()
    { return email;
    }
    public void setEmail(String email)
    { this.email = email;
    }
    public String getMobileNo()
    { return mobileNo;
    }
    public void setMobileNo(String mobileNo)
    { this.mobileNo = mobileNo;
    }
    public String getUsername()
    { return username;
    }
    public void setUsername(String username)
    { this.username = username;
    }
    public String getPassword()
    { return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterRequest that = (RegisterRequest) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(email, that.email) && Objects.equals(mobileNo, that.mobileNo) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(companyName, email, mobileNo, username, password);
    }
}