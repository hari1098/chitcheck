package com.chitcheck.myProfile.directordetails.service;

import com.chitcheck.auth.model.User;
import com.chitcheck.myProfile.companyProfile.model.CompanyProfile;
import com.chitcheck.myProfile.companyProfile.repository.CompanyProfileRepository;
import com.chitcheck.myProfile.directordetails.dto.DirectorDto;
import com.chitcheck.myProfile.directordetails.exception.ResourceNotFoundException;
import com.chitcheck.myProfile.directordetails.model.Director;
import com.chitcheck.myProfile.directordetails.repository.DirectorRepository;
import com.chitcheck.myProfile.status.StatusService;
import com.chitcheck.myProfile.status.StatusType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final CompanyProfileRepository companyProfileRepository;
    private final StatusService statusService;

    public DirectorService(DirectorRepository directorRepository, CompanyProfileRepository companyProfileRepository, StatusService statusService) {
        this.directorRepository = directorRepository;
        this.companyProfileRepository = companyProfileRepository;
        this.statusService = statusService;
    }

    public DirectorDto createDirector(User user, DirectorDto directorDto) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user. Please create one first."));
        Director director = new Director();
        mapDtoToEntity(directorDto, director);
        director.setCompanyProfile(profile);
        Director savedDirector = directorRepository.save(director);
        statusService.createStatusUpdate(profile, StatusType.INFO, "New director '" + savedDirector.getName() + "' was added to the profile.");
        return mapEntityToDto(savedDirector);
    }

    public DirectorDto updateDirector(Integer directorId, DirectorDto directorDto, User user) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user."));
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        if (!director.getCompanyProfile().getId().equals(profile.getId())) {
            throw new AccessDeniedException("You do not have permission to update this director.");
        }
        mapDtoToEntity(directorDto, director);
        Director updatedDirector = directorRepository.save(director);
        statusService.createStatusUpdate(profile, StatusType.INFO, "Details for director '" + updatedDirector.getName() + "' were updated.");
        return mapEntityToDto(updatedDirector);
    }

    public List<DirectorDto> getDirectorsForCompany(User user) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user."));
        return directorRepository.findByCompanyProfileId(profile.getId())
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public DirectorDto getDirectorById(Integer directorId, User user) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user."));
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        if (!director.getCompanyProfile().getId().equals(profile.getId())) {
            throw new AccessDeniedException("You do not have permission to view this director.");
        }
        return mapEntityToDto(director);
    }

    public void deleteDirector(Integer directorId, User user) {
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for user."));
        Director director = directorRepository.findById(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        if (!director.getCompanyProfile().getId().equals(profile.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this director.");
        }
        directorRepository.deleteById(directorId);
        statusService.createStatusUpdate(profile, StatusType.WARNING, "Director '" + director.getName() + "' was deleted.");
    }

    private DirectorDto mapEntityToDto(Director director) {
        DirectorDto dto = new DirectorDto();
        dto.setId(director.getId());
        dto.setName(director.getName());
        dto.setPancard(director.getPancard());
        dto.setAadharCard(director.getAadharCard());
        dto.setDirectorType(director.getDirectorType());
        dto.setJoinDate(director.getJoinDate());
        dto.setEmail(director.getEmail());
        dto.setMobileNo(director.getMobileNo());
        dto.setAddress(director.getAddress());
        return dto;
    }

    private void mapDtoToEntity(DirectorDto dto, Director director) {
        director.setName(dto.getName());
        director.setPancard(dto.getPancard());
        director.setAadharCard(dto.getAadharCard());
        director.setDirectorType(dto.getDirectorType());
        director.setJoinDate(dto.getJoinDate());
        director.setEmail(dto.getEmail());
        director.setMobileNo(dto.getMobileNo());
        director.setAddress(dto.getAddress());
    }
}