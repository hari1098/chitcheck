package com.chitcheck.myProfile.proofdetails;

import com.chitcheck.auth.model.User;
import com.chitcheck.myProfile.companyProfile.model.CompanyProfile;
import com.chitcheck.myProfile.companyProfile.repository.CompanyProfileRepository;
import com.chitcheck.exception.ResourceNotFoundException;
import com.chitcheck.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProofDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ProofDetailsService.class);

    private final CompanyProfileRepository companyProfileRepository;
    private final ProofDetailsRepository proofDetailsRepository;
    private final FileStorageService fileStorageService;

    public ProofDetailsService(CompanyProfileRepository companyProfileRepository, ProofDetailsRepository proofDetailsRepository, FileStorageService fileStorageService) {
        this.companyProfileRepository = companyProfileRepository;
        this.proofDetailsRepository = proofDetailsRepository;
        this.fileStorageService = fileStorageService;
    }

    public String uploadProofs(User user, MultipartFile gstCertificate, MultipartFile pancardFile, MultipartFile cinFile) throws IOException {
        logger.debug("Starting proof upload for user: {}", user.getId());
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for this user."));

        ProofDetails proofDetails = proofDetailsRepository.findByCompanyProfileId(profile.getId())
                .orElseGet(ProofDetails::new);

        proofDetails.setCompanyProfile(profile);

        logger.debug("Processing files - gstCertificate: {}, pancard: {}, cin: {}",
                gstCertificate != null ? gstCertificate.getOriginalFilename() : "null",
                pancardFile != null ? pancardFile.getOriginalFilename() : "null",
                cinFile != null ? cinFile.getOriginalFilename() : "null");

        if (gstCertificate != null && !gstCertificate.isEmpty()) {
            String path = fileStorageService.saveFile(gstCertificate);
            logger.debug("Returned path from saveFile for gstCertificate: {}", path);
            proofDetails.setGstCertificatePath(path != null ? path : "upload_failed");
            logger.debug("Set gstCertificatePath: {}", path);
        }
        if (pancardFile != null && !pancardFile.isEmpty()) {
            String path = fileStorageService.saveFile(pancardFile);
            logger.debug("Returned path from saveFile for pancard: {}", path);
            proofDetails.setPancardPath(path != null ? path : "upload_failed");
            logger.debug("Set pancardPath: {}", path);
        }
        if (cinFile != null && !cinFile.isEmpty()) {
            String path = fileStorageService.saveFile(cinFile);
            logger.debug("Returned path from saveFile for cin: {}", path);
            proofDetails.setCinPath(path != null ? path : "upload_failed");
            logger.debug("Set cinPath: {}", path);
        }

        proofDetailsRepository.save(proofDetails);
        logger.info("Proof details saved for user: {}, paths: {}, {}, {}", user.getId(),
                proofDetails.getGstCertificatePath(), proofDetails.getPancardPath(), proofDetails.getCinPath());
        return "Proofs uploaded successfully.";
    }

    @Transactional(readOnly = true)
    public ProofDetails getProofsForUser(User user) {
        logger.debug("Fetching proofs for user: {}", user.getId());
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for this user."));

        return proofDetailsRepository.findByCompanyProfileId(profile.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Proof details not found for this user."));
    }

    public void deleteProofDetails(Integer proofId, User user) {
        logger.debug("Deleting proof details with id: {} for user: {}", proofId, user.getId());
        CompanyProfile profile = companyProfileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Profile not found for this user."));

        ProofDetails proofDetails = proofDetailsRepository.findById(proofId)
                .orElseThrow(() -> new ResourceNotFoundException("Proof details not found with id: " + proofId));

        if (!proofDetails.getCompanyProfile().getId().equals(profile.getId())) {
            throw new AccessDeniedException("You do not have permission to delete these proofs.");
        }

        proofDetailsRepository.deleteById(proofId);
        logger.info("Proof details deleted for id: {}", proofId);
    }
}