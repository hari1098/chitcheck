package com.chitcheck.defaulters.bonds_details;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bond-details")
public class BondDetailsController {

    private final BondDetailsService bondService;

    public BondDetailsController(BondDetailsService bondService) {
        this.bondService = bondService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<BondDetails> createBond(
            @Valid @RequestPart("details") BondDetailsDto detailsDto,
            @RequestPart(value = "uploadCopy", required = false) MultipartFile file
    ) throws IOException {
        BondDetails createdBond = bondService.createBond(detailsDto, file);
        return new ResponseEntity<>(createdBond, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BondDetails>> getAllBonds() {
        return ResponseEntity.ok(bondService.getAllBonds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BondDetails> getBondById(@PathVariable Integer id) {
        return ResponseEntity.ok(bondService.getBondById(id));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<BondDetails> updateBond(
            @PathVariable Integer id,
            @Valid @RequestPart("details") BondDetailsDto detailsDto,
            @RequestPart(value = "uploadCopy", required = false) MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(bondService.updateBond(id, detailsDto, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBond(@PathVariable Integer id) {
        bondService.deleteBond(id);
        return ResponseEntity.noContent().build();
    }
}