package com.chitcheck.myProfile.companyProfile.repository; // Or your repository package

import com.chitcheck.myProfile.companyProfile.model.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Integer> {
    // A custom method to find a profile based on the user's ID
    Optional<CompanyProfile> findByUserId(Integer userId);
}