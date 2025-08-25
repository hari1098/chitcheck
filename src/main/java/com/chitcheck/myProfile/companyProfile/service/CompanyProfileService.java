package com.chitcheck.myProfile.companyProfile.service;

import com.chitcheck.auth.model.User;
import com.chitcheck.exception.ResourceNotFoundException;
import com.chitcheck.myProfile.companyProfile.model.CompanyProfile;
import com.chitcheck.myProfile.companyProfile.repository.CompanyProfileRepository;
import com.chitcheck.service.FileStorageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class CompanyProfileService {

    private final CompanyProfileRepository profileRepository;
    private final FileStorageService fileStorageService; // Assuming FileStorageService is used

    public CompanyProfileService(CompanyProfileRepository profileRepository, FileStorageService fileStorageService) {
        this.profileRepository = profileRepository;
        this.fileStorageService = fileStorageService;
    }

    public String createOrUpdateProfile(
            User user, String companyName, String contactNo, String email, String address,
            String contactPerson, String pancard, String cin, String gstNumber,
            MultipartFile profileImage, MultipartFile pancardImage,
            MultipartFile cinImage, MultipartFile gstImage) throws IOException {

        CompanyProfile profile = profileRepository.findByUserId(user.getId())
                .orElse(new CompanyProfile());
        profile.setUser(user);
        profile.setCompanyName(companyName);
        profile.setContactNo(contactNo);
        profile.setEmail(email);
        profile.setAddress(address);
        profile.setContactPerson(contactPerson);
        profile.setPancard(pancard);
        profile.setCin(cin);
        profile.setGstNumber(gstNumber);

        if (profileImage != null && !profileImage.isEmpty()) {
            profile.setProfileImagePath(fileStorageService.saveFile(profileImage));
        }
        if (pancardImage != null && !pancardImage.isEmpty()) {
            profile.setPancardImagePath(fileStorageService.saveFile(pancardImage));
        }
        if (cinImage != null && !cinImage.isEmpty()) {
            profile.setCinImagePath(fileStorageService.saveFile(cinImage));
        }
        if (gstImage != null && !gstImage.isEmpty()) {
            profile.setGstImagePath(fileStorageService.saveFile(gstImage));
        }

        profileRepository.save(profile);
        return "Profile updated successfully.";
    }

    @Transactional
    public CompanyProfile getProfileByUser(User user) {
        return profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for this user."));
    }

    // NEW METHOD TO GET A PROFILE BY ITS ID
    public CompanyProfile getProfileById(Integer profileId, User user) {
        // Find the profile by its own ID
        CompanyProfile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + profileId));

        // Security Check: Make sure the requested profile belongs to the logged-in user
        if (!profile.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to view this profile.");
        }

        return profile;
    }

    public void deleteProfile(User user) {
        CompanyProfile profile = getProfileByUser(user);
        profileRepository.deleteById(profile.getId());
    }
}