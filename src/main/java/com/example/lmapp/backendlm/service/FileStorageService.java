package com.example.lmapp.backendlm.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";

    public String store(MultipartFile file) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
        dir.mkdirs();
    }

        String originalFileName = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        Path targetLocation = Paths.get(uploadDir + File.separator + storedFileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return storedFileName;
    }
    
}
