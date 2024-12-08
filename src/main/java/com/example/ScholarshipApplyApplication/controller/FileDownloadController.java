package com.example.ScholarshipApplyApplication.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:3000")

public class FileDownloadController {

    private final String FILE_STORAGE_LOCATION = "C:\\uploads\\scholarship_applications";

    @GetMapping("/{username}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String username,
            @PathVariable String fileName) {
        try {
            // Construct the file path
            Path filePath = Paths.get(FILE_STORAGE_LOCATION, username, fileName).normalize();

            // Load the file as a resource
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Return the file resource
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
