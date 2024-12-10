package com.example.ScholarshipApplyApplication.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "https://scholarship-management-mu.vercel.app/")

public class FileController {

    private final String FILE_STORAGE_LOCATION = "C:\\uploads\\scholarship_applications";

    @GetMapping("/{username}")
    public ResponseEntity<?> getProofs(@PathVariable String username) {
        try {
            // Construct the directory path for the user's files
            File userDirectory = new File(FILE_STORAGE_LOCATION, username);

            // Check if the directory exists and is a directory
            if (!userDirectory.exists() || !userDirectory.isDirectory()) {
                return ResponseEntity.status(404).body("No proofs or documents available");
            }

            // List all files in the directory
            List<String> files = Arrays.stream(userDirectory.listFiles())
                    .filter(File::isFile) // Only include files, not subdirectories
                    .map(File::getName) // Get file names
                    .collect(Collectors.toList());

            // Return the list of files
            return ResponseEntity.ok(files);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving proofs");
        }
    }
}
