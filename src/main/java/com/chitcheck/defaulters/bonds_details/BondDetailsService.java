package com.chitcheck.defaulters.bonds_details;

import com.chitcheck.defaulters.personal_details.PersonalDetails;
import com.chitcheck.defaulters.personal_details.PersonalDetailsRepository;
import com.chitcheck.exception.ResourceNotFoundException;
import com.chitcheck.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BondDetailsService {

    private final BondDetailsRepository bondDetailsRepository;
    private final PersonalDetailsRepository personalDetailsRepository;
    private final FileStorageService fileStorageService;

    public BondDetailsService(BondDetailsRepository bondDetailsRepository, PersonalDetailsRepository personalDetailsRepository, FileStorageService fileStorageService) {
        this.bondDetailsRepository = bondDetailsRepository;
        this.personalDetailsRepository = personalDetailsRepository;
        this.fileStorageService = fileStorageService;
    }

    public BondDetails createBond(BondDetailsDto dto, MultipartFile file) throws IOException {
        PersonalDetails personalDetails = personalDetailsRepository.findById(dto.getPersonalDetailsId())
                .orElseThrow(() -> new ResourceNotFoundException("Personal Details not found with id: " + dto.getPersonalDetailsId()));

        BondDetails bond = new BondDetails();
        mapDtoToEntity(dto, bond);
        bond.setPersonalDetails(personalDetails);
        bond.setFilePath(fileStorageService.saveFile(file));

        return bondDetailsRepository.save(bond);
    }

    public List<BondDetails> getAllBonds() {
        return bondDetailsRepository.findAll();
    }

    public BondDetails getBondById(Integer id) {
        return bondDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bond Details not found with id: " + id));
    }

    public BondDetails updateBond(Integer id, BondDetailsDto dto, MultipartFile file) throws IOException {
        BondDetails existingBond = getBondById(id);
        mapDtoToEntity(dto, existingBond);
        if (file != null && !file.isEmpty()) {
            existingBond.setFilePath(fileStorageService.saveFile(file));
        }
        return bondDetailsRepository.save(existingBond);
    }

    public void deleteBond(Integer id) {
        if (!bondDetailsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bond Details not found with id: " + id);
        }
        bondDetailsRepository.deleteById(id);
    }

    private void mapDtoToEntity(BondDetailsDto dto, BondDetails entity) {
        entity.setBondType(dto.getBondType());
        entity.setBondNumber(dto.getBondNumber());
    }
}