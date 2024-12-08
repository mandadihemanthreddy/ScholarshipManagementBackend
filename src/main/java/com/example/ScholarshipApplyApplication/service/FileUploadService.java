package com.example.ScholarshipApplyApplication.service;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    private final String uploadDir = "C:\\uploads\\scholarship_applications\\";

    public FileUploadService() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload directory", e);
        }
    }

    public String storeFile(MultipartFile file, String username) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        // Create a folder for the user
        Path userFolder = Paths.get(uploadDir + username);
        Files.createDirectories(userFolder);

        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = userFolder.resolve(fileName);

        // Write the file to the path
        Files.write(filePath, file.getBytes());
        return filePath.toString(); // Return the absolute path to store in the database
    }
}
