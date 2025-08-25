package com.chitcheck.defaulters.case_details;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CaseDetailsRepository extends JpaRepository<CaseDetails, Integer> {
    List<CaseDetails> findByPersonalDetailsId(Integer personalDetailsId);
}