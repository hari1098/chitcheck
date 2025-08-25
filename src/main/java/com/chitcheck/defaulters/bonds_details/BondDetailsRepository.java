package com.chitcheck.defaulters.bonds_details;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BondDetailsRepository extends JpaRepository<BondDetails, Integer> {
    List<BondDetails> findByPersonalDetailsId(Integer personalDetailsId);
}