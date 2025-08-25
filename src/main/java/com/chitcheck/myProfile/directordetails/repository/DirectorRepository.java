package com.chitcheck.myProfile.directordetails.repository;

import com.chitcheck.myProfile.directordetails.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Integer> {
    // Find all directors associated with a specific company profile ID
    List<Director> findByCompanyProfileId(Integer profileId);
}