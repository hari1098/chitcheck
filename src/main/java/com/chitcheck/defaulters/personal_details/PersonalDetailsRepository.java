package com.chitcheck.defaulters.personal_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Integer> {

    @Query("SELECT p FROM PersonalDetails p WHERE " +
            "(:name IS NULL OR p.subscriberName LIKE %:name%) AND " +
            "(:pancard IS NULL OR p.panCard = :pancard) AND " +
            "(:aadhar IS NULL OR p.aadharCard = :aadhar) AND " +
            "(:mobile IS NULL OR p.phoneNo = :mobile)")
    List<PersonalDetails> search(
            @Param("name") String name,
            @Param("pancard") String pancard,
            @Param("aadhar") String aadhar,
            @Param("mobile") String mobile
    );
}