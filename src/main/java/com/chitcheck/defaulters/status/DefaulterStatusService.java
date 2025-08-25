package com.chitcheck.defaulters.status;

import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.chitcheck.defaulters.personal_details.PersonalDetailsRepository;
import com.chitcheck.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List; // <-- ADD THIS IMPORT

@Service
public class DefaulterStatusService {

    private final DefaulterStatusRepository statusRepository;
    private final PersonalDetailsRepository personalDetailsRepository;

    public DefaulterStatusService(DefaulterStatusRepository statusRepository, PersonalDetailsRepository personalDetailsRepository) {
        this.statusRepository = statusRepository;
        this.personalDetailsRepository = personalDetailsRepository;
    }

    public DefaulterStatus createOrUpdateStatus(DefaulterStatusDto dto) {
        PersonalDetails personalDetails = personalDetailsRepository.findById(dto.getPersonalDetailsId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal Details not found with id: " + dto.getPersonalDetailsId()));

        DefaulterStatus status = statusRepository.findByPersonalDetailsId(personalDetails.getId())
                .orElse(new DefaulterStatus());

        status.setPersonalDetails(personalDetails);
        status.setStatus(dto.getStatus());
        status.setStatusDescription(dto.getStatusDescription());

        return statusRepository.save(status);
    }

    // NEW METHOD TO GET ALL STATUS RECORDS
    public List<DefaulterStatus> getAllStatuses() {
        return statusRepository.findAll();
    }

    public DefaulterStatus getStatusForPersonalDetails(Integer personalDetailsId) {
        return statusRepository.findByPersonalDetailsId(personalDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found for Personal Details with id: " + personalDetailsId));
    }

    public void deleteStatus(Integer id) {
        if (!statusRepository.existsById(id)) {
            throw new ResourceNotFoundException("Status not found with id: " + id);
        }
        statusRepository.deleteById(id);
    }
}