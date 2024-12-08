package com.example.ScholarshipApplyApplication.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.nio.file.*;

@RestController
@RequestMapping("/api")
public class SimpleController {

    // Directory where files will be saved
    private static final String UPLOAD_DIR = "C:/uploads/";

    @PostMapping("/testUpload")
    public ResponseEntity<String> testUpload(@RequestParam("file") MultipartFile file) {
        // Check if file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }

        // Ensure the directory exists
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file
            Path targetLocation = uploadPath.resolve(file.getOriginalFilename());
            file.transferTo(targetLocation.toFile());

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
