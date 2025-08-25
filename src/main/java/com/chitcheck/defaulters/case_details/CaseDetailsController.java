package com.chitcheck.defaulters.case_details;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/case-details")
public class CaseDetailsController {

    private final CaseDetailsService caseService;

    public CaseDetailsController(CaseDetailsService caseService) {
        this.caseService = caseService;
    }

    @PostMapping
    public ResponseEntity<CaseDetails> createCase(@Valid @RequestBody CaseDetailsDto dto) {
        CaseDetails createdCase = caseService.createCase(dto);
        return new ResponseEntity<>(createdCase, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CaseDetails>> getAllCases() {
        return ResponseEntity.ok(caseService.getAllCases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseDetails> getCaseById(@PathVariable Integer id) {
        return ResponseEntity.ok(caseService.getCaseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseDetails> updateCase(@PathVariable Integer id, @Valid @RequestBody CaseDetailsDto dto) {
        return ResponseEntity.ok(caseService.updateCase(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable Integer id) {
        caseService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }
}