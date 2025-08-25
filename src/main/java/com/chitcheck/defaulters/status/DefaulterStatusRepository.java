package com.chitcheck.defaulters.status;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DefaulterStatusRepository extends JpaRepository<DefaulterStatus, Integer> {
    Optional<DefaulterStatus> findByPersonalDetailsId(Integer personalDetailsId);
}