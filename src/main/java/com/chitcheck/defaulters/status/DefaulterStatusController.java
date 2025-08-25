package com.chitcheck.defaulters.status;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <-- ADD THIS IMPORT

@RestController
@RequestMapping("/api/v1/defaulter-status")
public class DefaulterStatusController {

    private final DefaulterStatusService statusService;

    public DefaulterStatusController(DefaulterStatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<DefaulterStatus> createOrUpdateStatus(@Valid @RequestBody DefaulterStatusDto dto) {
        DefaulterStatus savedStatus = statusService.createOrUpdateStatus(dto);
        return ResponseEntity.ok(savedStatus);
    }

    // NEW ENDPOINT TO GET ALL STATUS RECORDS
    @GetMapping
    public ResponseEntity<List<DefaulterStatus>> getAllStatuses() {
        return ResponseEntity.ok(statusService.getAllStatuses());
    }

    @GetMapping("/by-person/{personalDetailsId}")
    public ResponseEntity<DefaulterStatus> getStatusForPerson(@PathVariable Integer personalDetailsId) {
        return ResponseEntity.ok(statusService.getStatusForPersonalDetails(personalDetailsId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Integer id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}