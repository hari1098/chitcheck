package com.chitcheck.defaulters.guarantor_details;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GuarantorDetailsRepository extends JpaRepository<GuarantorDetails, Integer> {
    List<GuarantorDetails> findByPersonalDetailsId(Integer personalDetailsId);
}