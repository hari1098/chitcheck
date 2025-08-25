package com.chitcheck.defaulters.guarantor_details;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/guarantor-details")
public class GuarantorDetailsController {

    private final GuarantorDetailsService guarantorService;

    public GuarantorDetailsController(GuarantorDetailsService guarantorService) {
        this.guarantorService = guarantorService;
    }

    @PostMapping
    public ResponseEntity<GuarantorDetails> createGuarantor(@Valid @RequestBody GuarantorDetailsDto dto) {
        GuarantorDetails createdGuarantor = guarantorService.createGuarantor(dto);
        return new ResponseEntity<>(createdGuarantor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GuarantorDetails>> getAllGuarantors() {
        return ResponseEntity.ok(guarantorService.getAllGuarantors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuarantorDetails> getGuarantorById(@PathVariable Integer id) {
        return ResponseEntity.ok(guarantorService.getGuarantorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuarantorDetails> updateGuarantor(@PathVariable Integer id, @Valid @RequestBody GuarantorDetailsDto dto) {
        return ResponseEntity.ok(guarantorService.updateGuarantor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuarantor(@PathVariable Integer id) {
        guarantorService.deleteGuarantor(id);
        return ResponseEntity.noContent().build();
    }
}