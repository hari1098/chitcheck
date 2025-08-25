package com.chitcheck.myProfile.status;

import com.chitcheck.auth.model.User;
import com.chitcheck.myProfile.companyProfile.model.CompanyProfile;
import com.chitcheck.myProfile.companyProfile.repository.CompanyProfileRepository;
import com.chitcheck.myProfile.directordetails.exception.ResourceNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService {

    private final StatusUpdateRepository statusUpdateRepository;
    private final CompanyProfileRepository companyProfileRepository;

    public StatusService(StatusUpdateRepository statusUpdateRepository, CompanyProfileRepository companyProfileRepository) {
        this.statusUpdateRepository = statusUpdateRepository;
        this.companyProfileRepository = companyProfileRepository;
    }

    public void createStatusUpdate(CompanyProfile profile, StatusType type, String description) {
        StatusUpdate statusUpdate = new StatusUpdate();
        statusUpdate.setCompanyProfile(profile);
        statusUpdate.setStatusType(type);
        statusUpdate.setDescription(description);
        statusUpdateRepository.save(statusUpdate);
    }

    public List<StatusUpdateDto> getStatusUpdatesForCompany(User user) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user."));

        return statusUpdateRepository.findByCompanyProfileIdOrderByTimestampDesc(profile.getId())
                .stream()
                .map(status -> new StatusUpdateDto(
                        status.getDescription(),
                        status.getStatusType().name(),
                        status.getTimestamp()
                ))
                .collect(Collectors.toList());
    }

    // NEW METHOD TO GET A SINGLE STATUS UPDATE BY ID
    public StatusUpdateDto getStatusUpdateById(Long statusId, User user) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user."));
        StatusUpdate status = statusUpdateRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Status update not found with id: " + statusId));

        if(!status.getCompanyProfile().getId().equals(profile.getId())) {
            throw new AccessDeniedException("You do not have permission to view this status update.");
        }

        return new StatusUpdateDto(status.getDescription(), status.getStatusType().name(), status.getTimestamp());
    }

    // NEW METHOD TO DELETE A STATUS UPDATE
    public void deleteStatusUpdate(Long statusId, User user) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user."));
        StatusUpdate status = statusUpdateRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("Status update not found with id: " + statusId));

        if(!status.getCompanyProfile().getId().equals(profile.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this status update.");
        }

        statusUpdateRepository.deleteById(statusId);
    }
}