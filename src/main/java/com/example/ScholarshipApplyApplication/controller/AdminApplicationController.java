package com.example.ScholarshipApplyApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ScholarshipApplyApplication.entity.ScholarshipApplication;
import com.example.ScholarshipApplyApplication.repository.ScholarshipApplicationRepository;
import com.example.ScholarshipApplyApplication.service.CustomMailSender;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scholarship")
@CrossOrigin(origins = "https://scholarship-management-mu.vercel.app/") // Allow frontend access
public class AdminApplicationController {

    @Autowired
    private ScholarshipApplicationRepository scholarshipApplicationRepository;

    @Autowired
    private CustomMailSender customMailSender; // Inject the custom email service

    // Fetch all scholarship applications
    @GetMapping("/applications")
    public ResponseEntity<List<ScholarshipApplication>> getAllApplications() {
        List<ScholarshipApplication> applications = scholarshipApplicationRepository.findAll();
        return ResponseEntity.ok(applications);
    }

    // Fetch applications by username
    @GetMapping("/applications/username/{username}")
    public ResponseEntity<List<ScholarshipApplication>> getApplicationsByUsername(@PathVariable String username) {
        List<ScholarshipApplication> applications = scholarshipApplicationRepository.findByUsername(username);
        if (applications.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(applications);
    }

    // Fetch single scholarship application by ID
    @GetMapping("/applications/{id}")
    public ResponseEntity<ScholarshipApplication> getApplicationById(@PathVariable Long id) {
        Optional<ScholarshipApplication> application = scholarshipApplicationRepository.findById(id);
        if (application.isPresent()) {
            return ResponseEntity.ok(application.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Update scholarship application status and send email notification
    @PutMapping("/applications/{id}")
    public ResponseEntity<String> updateApplication(@PathVariable Long id,
                                                    @RequestParam String status,
                                                    @RequestParam String remarks) {
        Optional<ScholarshipApplication> applicationOpt = scholarshipApplicationRepository.findById(id);
        if (!applicationOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not found");
        }
        
        ScholarshipApplication application = applicationOpt.get();
        application.setStatus(status);
        application.setRemarks(remarks);
        scholarshipApplicationRepository.save(application); // Save updated status and remarks
        
        // Send email notification
        String emailSubject;
        String emailBody;
        
        if ("accepted".equalsIgnoreCase(status)) {
            emailSubject = "Scholarship Application Accepted!";
            emailBody = "Dear " + application.getFullName() + ",\n\n" +
                        "Congratulations! Your application for the scholarship \"" + application.getScholarshipName() + 
                        "\" has been accepted.\n\nRemarks: " + remarks + "\n\n" +
                        "We wish you the best for your future endeavors!\n\n" +
                        "Stay tuned for more updates.\n\n" +
                        "Best regards,\nScholarship Team";
        } else if ("rejected".equalsIgnoreCase(status)) {
            emailSubject = "Scholarship Application Rejected";
            emailBody = "Dear " + application.getFullName() + ",\n\n" +
                        "We regret to inform you that your application for the scholarship \"" + application.getScholarshipName() + 
                        "\" has been rejected.\n\nRemarks: " + remarks + "\n\n" +
                        "Please do not lose heart. Stay tuned for other scholarship opportunities.\n\n" +
                        "Best regards,\nScholarship Team";
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status. Only 'accepted' or 'rejected' are allowed.");
        }

        try {
            customMailSender.sendEmail(application.getEmail(), emailSubject, emailBody);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email: " + e.getMessage());
        }

        return ResponseEntity.ok("Application updated successfully, and email notification sent.");
    }

    @GetMapping("/applications/token/{applicationToken}")
    public ResponseEntity<ScholarshipApplication> getApplicationByToken(@PathVariable String applicationToken) {
        Optional<ScholarshipApplication> applicationOpt = scholarshipApplicationRepository.findByApplicationToken(applicationToken);
        if (applicationOpt.isPresent()) {
            return ResponseEntity.ok(applicationOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
