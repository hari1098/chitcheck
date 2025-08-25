package com.chitcheck.defaulters.case_details;

import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.chitcheck.defaulters.personal_details.PersonalDetailsRepository;
import com.chitcheck.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CaseDetailsService {

    private final CaseDetailsRepository caseRepository;
    private final PersonalDetailsRepository personalDetailsRepository;

    public CaseDetailsService(CaseDetailsRepository caseRepository, PersonalDetailsRepository personalDetailsRepository) {
        this.caseRepository = caseRepository;
        this.personalDetailsRepository = personalDetailsRepository;
    }

    public CaseDetails createCase(CaseDetailsDto dto) {
        PersonalDetails personalDetails = personalDetailsRepository.findById(dto.getPersonalDetailsId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal Details not found with id: " + dto.getPersonalDetailsId()));

        CaseDetails caseDetails = new CaseDetails();
        mapDtoToEntity(dto, caseDetails);
        caseDetails.setPersonalDetails(personalDetails);
        return caseRepository.save(caseDetails);
    }

    public List<CaseDetails> getAllCases() {
        return caseRepository.findAll();
    }

    public CaseDetails getCaseById(Integer id) {
        return caseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Case Details not found with id: " + id));
    }

    public CaseDetails updateCase(Integer id, CaseDetailsDto dto) {
        CaseDetails existingCase = getCaseById(id);
        mapDtoToEntity(dto, existingCase);
        return caseRepository.save(existingCase);
    }

    public void deleteCase(Integer id) {
        if (!caseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Case Details not found with id: " + id);
        }
        caseRepository.deleteById(id);
    }

    private void mapDtoToEntity(CaseDetailsDto dto, CaseDetails entity) {
        entity.setCaseNumber(dto.getCaseNumber());
        entity.setCurrentStatus(dto.getCurrentStatus());
        entity.setDescription(dto.getDescription());
        entity.setRegisterOffice(dto.getRegisterOffice());
        entity.setNextHearingDate(dto.getNextHearingDate());
    }
}