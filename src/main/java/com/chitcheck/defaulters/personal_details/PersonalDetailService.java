package com.chitcheck.defaulters.personal_details;

import com.chitcheck.exception.ResourceNotFoundException;
import com.chitcheck.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PersonalDetailService {

    private final PersonalDetailsRepository personalDetailsRepository;
    private final FileStorageService fileStorageService;

    public PersonalDetailService(PersonalDetailsRepository personalDetailsRepository, FileStorageService fileStorageService) {
        this.personalDetailsRepository = personalDetailsRepository;
        this.fileStorageService = fileStorageService;
    }

    public PersonalDetails createPersonalDetails(PersonalDetailsDto dto, MultipartFile profileImage) throws IOException {
        PersonalDetails details = new PersonalDetails();
        mapDtoToEntity(dto, details);
        details.setProfileImagePath(fileStorageService.saveFile(profileImage));
        return personalDetailsRepository.save(details);
    }

    public List<PersonalDetails> getAllPersonalDetails() {
        return personalDetailsRepository.findAll();
    }

    public PersonalDetails getPersonalDetailsById(Integer id) {
        return personalDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personal Details not found with id: " + id));
    }

    public PersonalDetails updatePersonalDetails(Integer id, PersonalDetailsDto dto, MultipartFile profileImage) throws IOException {
        PersonalDetails existingDetails = getPersonalDetailsById(id);
        mapDtoToEntity(dto, existingDetails);
        if (profileImage != null && !profileImage.isEmpty()) {
            existingDetails.setProfileImagePath(fileStorageService.saveFile(profileImage));
        }
        return personalDetailsRepository.save(existingDetails);
    }

    public void deletePersonalDetails(Integer id) {
        if (!personalDetailsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Personal Details not found with id: " + id);
        }
        personalDetailsRepository.deleteById(id);
    }

    public List<PersonalDetails> searchPersonalDetails(String name, String pancard, String aadhar, String mobile) {
        return personalDetailsRepository.search(name, pancard, aadhar, mobile);
    }

    private void mapDtoToEntity(PersonalDetailsDto dto, PersonalDetails entity) {
        entity.setSubscriberName(dto.getSubscriberName());
        entity.setContactPerson(dto.getContactPerson());
        entity.setArea(dto.getArea());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNo(dto.getPhoneNo());
        entity.setEmail(dto.getEmail());
        entity.setIntroducer(dto.getIntroducer());
        entity.setNominee(dto.getNominee());
        entity.setRelationship(dto.getRelationship());
        entity.setPanCard(dto.getPanCard());
        entity.setSubscriberType(dto.getSubscriberType());
        entity.setAadharCard(dto.getAadharCard());
        entity.setGstNumber(dto.getGstNumber());
    }
}