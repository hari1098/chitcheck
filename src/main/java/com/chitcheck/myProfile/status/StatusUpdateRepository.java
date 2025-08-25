package com.chitcheck.myProfile.status;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StatusUpdateRepository extends JpaRepository<StatusUpdate, Long> {
    // Get all statuses for a company, newest first
    List<StatusUpdate> findByCompanyProfileIdOrderByTimestampDesc(Integer profileId);
}