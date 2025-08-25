package com.chitcheck.dashboard;

import com.chitcheck.defaulters.personal_details.PersonalDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final PersonalDetailsRepository personalDetailsRepository;

    // Manual constructor for dependency injection
    public DashboardService(PersonalDetailsRepository personalDetailsRepository) {
        this.personalDetailsRepository = personalDetailsRepository;
    }

    public DashboardStatsDto getDashboardStats() {
        // Use the built-in count() method from the JpaRepository
        long count = personalDetailsRepository.count();

        // Create a new DTO and set the value
        DashboardStatsDto stats = new DashboardStatsDto();
        stats.setTotalDefaulters(count);

        return stats;
    }
}