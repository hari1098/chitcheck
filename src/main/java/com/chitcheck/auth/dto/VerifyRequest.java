package com.chitcheck.auth.dto;

import java.util.Objects;

public class VerifyRequest {
    private String email;
    private String otp;


    public VerifyRequest() {
    }

    public VerifyRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifyRequest that = (VerifyRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(otp, that.otp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, otp);
    }

    @Override
    public String toString() {
        return "VerifyRequest{" +
                "email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}