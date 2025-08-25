package com.chitcheck.defaulters.personal_details;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/personal-details")
public class PersonalDetailsController {

    private final PersonalDetailService personalDetailService;

    public PersonalDetailsController(PersonalDetailService personalDetailService) {
        this.personalDetailService = personalDetailService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<PersonalDetails> createDetails(
            @Valid @RequestPart("details") PersonalDetailsDto detailsDto,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) throws IOException {
        PersonalDetails createdDetails = personalDetailService.createPersonalDetails(detailsDto, profileImage);
        return new ResponseEntity<>(createdDetails, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonalDetails>> getAllDetails() {
        return ResponseEntity.ok(personalDetailService.getAllPersonalDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalDetails> getDetailsById(@PathVariable Integer id) {
        return ResponseEntity.ok(personalDetailService.getPersonalDetailsById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PersonalDetails>> searchDetails(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pancard", required = false) String pancard,
            @RequestParam(value = "aadhar", required = false) String aadhar,
            @RequestParam(value = "mobile", required = false) String mobile
    ) {
        List<PersonalDetails> results = personalDetailService.searchPersonalDetails(name, pancard, aadhar, mobile);
        return ResponseEntity.ok(results);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<PersonalDetails> updateDetails(
            @PathVariable Integer id,
            @Valid @RequestPart("details") PersonalDetailsDto detailsDto,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) throws IOException {
        PersonalDetails updatedDetails = personalDetailService.updatePersonalDetails(id, detailsDto, profileImage);
        return ResponseEntity.ok(updatedDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetails(@PathVariable Integer id) {
        personalDetailService.deletePersonalDetails(id);
        return ResponseEntity.noContent().build();
    }
}