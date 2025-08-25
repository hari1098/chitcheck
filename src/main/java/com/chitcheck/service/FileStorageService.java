package com.chitcheck.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            logger.warn("Received null or empty file: {}", file);
            return null;
        }

        try {
            // Resolve uploadDir as an absolute path to avoid relative path issues
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath();
            logger.debug("Resolved uploadDir as absolute path: {}", uploadPath);

            if (!Files.exists(uploadPath)) {
                logger.info("Creating upload directory: {}", uploadPath);
                Files.createDirectories(uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + (originalFilename != null ? originalFilename : "unnamed");
            Path filePath = uploadPath.resolve(uniqueFileName);
            logger.debug("Attempting to save file to: {}", filePath);

            // Ensure the input stream is valid before copying
            if (file.getInputStream() == null) {
                logger.error("File input stream is null for file: {}", originalFilename);
                return null;
            }

            Files.copy(file.getInputStream(), filePath);
            logger.info("File saved successfully: {}", uniqueFileName);

            // Return the absolute path for consistency
            return filePath.toString();
        } catch (IOException e) {
            logger.error("Failed to save file {}: {}", file.getOriginalFilename(), e.getMessage(), e);
            return null;
        }
    }
}