package com.example.ScholarshipApplyApplication.controller;

import com.example.ScholarshipApplyApplication.entity.ScholarshipEntity;
import com.example.ScholarshipApplyApplication.service.CustomMailSender;
import com.example.ScholarshipApplyApplication.service.ScholarshipService;
import com.example.ScholarshipApplyApplication.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin/scholarships")
public class ScholarshipController {

    private static final Logger logger = LoggerFactory.getLogger(ScholarshipController.class);

    @Autowired
    private ScholarshipService scholarshipService;
    
    @Autowired
    private UserService userService;
    
    @Autowired 
    private CustomMailSender mailSender; 

    // 1. Get all scholarships
    @GetMapping
    public ResponseEntity<List<ScholarshipEntity>> getAllScholarships() {
        List<ScholarshipEntity> scholarships = scholarshipService.getAllScholarships();
        logger.info("Retrieved scholarships: {}", scholarships);
        return new ResponseEntity<>(scholarships, HttpStatus.OK);
    }

    // 2. Get a single scholarship by ID
    @GetMapping("/{id}")
    public ResponseEntity<ScholarshipEntity> getScholarshipById(@PathVariable Long id) {
        Optional<ScholarshipEntity> scholarship = scholarshipService.getScholarshipById(id);
        if (scholarship.isPresent()) {
            return new ResponseEntity<>(scholarship.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 3. Add new scholarship
    @PostMapping
    public ResponseEntity<?> addScholarship(@RequestBody ScholarshipEntity scholarship) {
        logger.info("Adding scholarship: {}", scholarship);

        // Validate input fields
        if (scholarship.getName() == null || scholarship.getName().isEmpty()) {
            return new ResponseEntity<>("Scholarship name is required", HttpStatus.BAD_REQUEST);
        }
        if (scholarship.getAmount() == null || scholarship.getAmount() <= 0) {
            return new ResponseEntity<>("Valid scholarship amount is required", HttpStatus.BAD_REQUEST);
        }

        // Save the scholarship
        ScholarshipEntity savedScholarship = scholarshipService.addScholarship(scholarship);

        // Notify all users about the new scholarship
        try {
            List<String> userEmails = userService.getAllUserEmails(); // Fetch all user emails
            for (String email : userEmails) {
                mailSender.sendEmail(
                    email,
                    "New Scholarship Added: " + scholarship.getName(),
                    "A new scholarship has been added!\n\n" +
                    "Name: " + scholarship.getName() + "\n" +
                    "Description: " + scholarship.getDescription() + "\n\n" +
                    "Visit the portal to apply for this scholarship."
                );
            }
            logger.info("Notification emails sent to all users.");
        } catch (Exception e) {
            logger.error("Error sending notification emails: {}", e.getMessage());
            return new ResponseEntity<>("Scholarship added, but failed to notify users.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(savedScholarship, HttpStatus.CREATED);
    }




    // 4. Update scholarship by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScholarship(@PathVariable Long id, @RequestBody ScholarshipEntity scholarshipDetails) {
        ScholarshipEntity updatedScholarship = scholarshipService.updateScholarship(id, scholarshipDetails);
        
        if (updatedScholarship != null) {
            return new ResponseEntity<>(updatedScholarship, HttpStatus.OK);
        }
        return new ResponseEntity<>("Scholarship not found", HttpStatus.NOT_FOUND);
    }
    

    // 5. Delete scholarship by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScholarship(@PathVariable Long id) {
        Optional<ScholarshipEntity> scholarship = scholarshipService.getScholarshipById(id);
        
        if (scholarship.isPresent()) {
            scholarshipService.deleteScholarship(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Scholarship not found", HttpStatus.NOT_FOUND);
    }
}
