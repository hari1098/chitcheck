package com.chitcheck.myProfile.proofdetails;

import com.chitcheck.auth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/proofs")
public class ProofDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(ProofDetailsController.class);

    private final ProofDetailsService proofDetailsService;

    public ProofDetailsController(ProofDetailsService proofDetailsService) {
        this.proofDetailsService = proofDetailsService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadProofs(
            @AuthenticationPrincipal User user,
            @RequestPart(value = "gstCertificate", required = false) MultipartFile gstCertificate,
            @RequestPart(value = "pancard", required = false) MultipartFile pancard,
            @RequestPart(value = "cin", required = false) MultipartFile cin,
            @RequestHeader HttpHeaders headers) {
        logger.debug("Received upload request for user: {}", user.getId());
        logger.debug("Request Headers: {}", headers);
        logger.debug("Files - gstCertificate: {}, pancard: {}, cin: {}",
                gstCertificate != null ? gstCertificate.getOriginalFilename() : "null",
                pancard != null ? pancard.getOriginalFilename() : "null",
                cin != null ? cin.getOriginalFilename() : "null");

        try {
            String result = proofDetailsService.uploadProofs(user, gstCertificate, pancard, cin);
            logger.info("Proofs uploaded successfully for user: {}", user.getId());
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            logger.error("Error uploading proofs: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Error uploading proofs: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ProofDetails> getProofs(@AuthenticationPrincipal User user) {
        logger.debug("Fetching proofs for user: {}", user.getId());
        ProofDetails proofs = proofDetailsService.getProofsForUser(user);
        if (proofs == null) {
            logger.warn("No proofs found for user: {}", user.getId());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proofDetailsService.getProofsForUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProofs(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        logger.debug("Deleting proofs with id: {} for user: {}", id, user.getId());
        proofDetailsService.deleteProofDetails(id, user);
        logger.info("Proofs deleted successfully for id: {}", id);
        return ResponseEntity.noContent().build();
    }
}