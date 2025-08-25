package com.chitcheck.defaulters.group_details;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupDetailsRepository extends JpaRepository<GroupDetails, Integer> {
    List<GroupDetails> findByPersonalDetailsId(Integer personalDetailsId);
}