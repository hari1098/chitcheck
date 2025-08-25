package com.chitcheck.defaulters.guarantor_details;

import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.chitcheck.defaulters.personal_details.PersonalDetailsRepository;
import com.chitcheck.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GuarantorDetailsService {

    private final GuarantorDetailsRepository guarantorRepository;
    private final PersonalDetailsRepository personalDetailsRepository;

    public GuarantorDetailsService(GuarantorDetailsRepository guarantorRepository, PersonalDetailsRepository personalDetailsRepository) {
        this.guarantorRepository = guarantorRepository;
        this.personalDetailsRepository = personalDetailsRepository;
    }

    public GuarantorDetails createGuarantor(GuarantorDetailsDto dto) {
        PersonalDetails personalDetails = personalDetailsRepository.findById(dto.getPersonalDetailsId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal Details not found with id: " + dto.getPersonalDetailsId()));

        GuarantorDetails guarantor = new GuarantorDetails();
        mapDtoToEntity(dto, guarantor);
        guarantor.setPersonalDetails(personalDetails);
        return guarantorRepository.save(guarantor);
    }

    public List<GuarantorDetails> getAllGuarantors() {
        return guarantorRepository.findAll();
    }

    public GuarantorDetails getGuarantorById(Integer id) {
        return guarantorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guarantor Details not found with id: " + id));
    }

    public GuarantorDetails updateGuarantor(Integer id, GuarantorDetailsDto dto) {
        GuarantorDetails existingGuarantor = getGuarantorById(id);
        mapDtoToEntity(dto, existingGuarantor);
        return guarantorRepository.save(existingGuarantor);
    }

    public void deleteGuarantor(Integer id) {
        if (!guarantorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Guarantor Details not found with id: " + id);
        }
        guarantorRepository.deleteById(id);
    }

    private void mapDtoToEntity(GuarantorDetailsDto dto, GuarantorDetails entity) {
        entity.setSuretyName(dto.getSuretyName());
        entity.setFatherOrHusbandName(dto.getFatherOrHusbandName());
        entity.setMobileNo(dto.getMobileNo());
        entity.setAadharCardNo(dto.getAadharCardNo());
        entity.setAddress(dto.getAddress());
        entity.setAge(dto.getAge());
        entity.setOccupation(dto.getOccupation());
        entity.setEmailId(dto.getEmailId());
        entity.setPancard(dto.getPancard());
    }
}