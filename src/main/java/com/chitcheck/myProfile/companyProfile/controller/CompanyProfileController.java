package com.chitcheck.myProfile.companyProfile.controller;

import com.chitcheck.auth.model.User;
import com.chitcheck.myProfile.companyProfile.model.CompanyProfile;
import com.chitcheck.myProfile.companyProfile.service.CompanyProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/profile")
public class CompanyProfileController {

    private final CompanyProfileService profileService;

    public CompanyProfileController(CompanyProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, consumes = {"multipart/form-data"})
    public ResponseEntity<String> createOrUpdateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam("companyName") String companyName,
            @RequestParam("contactNo") String contactNo,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("contactPerson") String contactPerson,
            @RequestParam("pancard") String pancard,
            @RequestParam("cin") String cin,
            @RequestParam("gstNumber") String gstNumber,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestPart(value = "pancardImage", required = false) MultipartFile pancardImage,
            @RequestPart(value = "cinImage", required = false) MultipartFile cinImage,
            @RequestPart(value = "gstImage", required = false) MultipartFile gstImage
    ) {
        try {
            String result = profileService.createOrUpdateProfile(
                    user, companyName, contactNo, email, address, contactPerson,
                    pancard, cin, gstNumber, profileImage, pancardImage, cinImage, gstImage
            );
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading files: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<CompanyProfile> getProfile(@AuthenticationPrincipal User user) {
        CompanyProfile profile = profileService.getProfileByUser(user);
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profileService.getProfileByUser(user));

    }

    // NEW ENDPOINT TO GET A PROFILE BY ITS ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyProfile> getProfileById(
            @PathVariable Integer id,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(profileService.getProfileById(id, user));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfile(@AuthenticationPrincipal User user) {
        profileService.deleteProfile(user);
        return ResponseEntity.noContent().build();
    }
}