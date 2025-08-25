package com.chitcheck.myProfile.proofdetails; // <-- CORRECTED PACKAGE

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// This import is now correct because both files are in the same package
import com.chitcheck.myProfile.proofdetails.ProofDetails;

public interface ProofDetailsRepository extends JpaRepository<ProofDetails, Integer> {
    Optional<ProofDetails> findByCompanyProfileId(Integer profileId);
}