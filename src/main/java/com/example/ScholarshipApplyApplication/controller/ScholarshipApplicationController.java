package com.example.ScholarshipApplyApplication.controller;

import com.example.ScholarshipApplyApplication.entity.ScholarshipApplication;
import com.example.ScholarshipApplyApplication.entity.ScholarshipEntity;
import com.example.ScholarshipApplyApplication.entity.UserEntity;
import com.example.ScholarshipApplyApplication.service.CustomMailSender;
import com.example.ScholarshipApplyApplication.service.FileUploadService;
import com.example.ScholarshipApplyApplication.service.ScholarshipApplicationService;
import com.example.ScholarshipApplyApplication.service.ScholarshipService;
import com.example.ScholarshipApplyApplication.service.UserService;
import com.example.ScholarshipApplyApplication.utils.TokenUtils;
import com.example.ScholarshipApplyApplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/scholarship")
@CrossOrigin(origins = "https://scholarship-management-mu.vercel.app/") // Allow frontend access
public class ScholarshipApplicationController {

    @Autowired
    private ScholarshipApplicationService scholarshipApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private FileUploadService fileUploadService; // Handles file storage
    
    @Autowired
    private ScholarshipApplicationRepository scholarshipApplicationRepository;

    @Autowired 
    private TokenUtils tokenUtils;
    
    @Autowired
    private CustomMailSender customMailSender;
    /**
     * Submit a scholarship application.
     */
    @PostMapping("/apply")
    public ResponseEntity<String> applyForScholarship(
            @RequestParam("username") String username,
            @RequestParam("scholarshipId") Long scholarshipId,
            @RequestParam(required = false) MultipartFile proofOfCitizenship,
            @RequestParam(required = false) MultipartFile proofOfStudying,
            @RequestParam(required = false) MultipartFile proofOfFinancial,
            @RequestParam(required = false) MultipartFile proofOfMerits,
            @RequestParam("country") String country,
            @RequestParam("state") String state,
            @RequestParam("city") String city,
            @RequestParam("mandal") String mandal,
            @RequestParam("village") String village,
            @RequestParam("addressLine1") String addressLine1,
            @RequestParam("studyType") String studyType,
            @RequestParam("schoolName") String schoolName,
            @RequestParam("classStudying") String classStudying,
            @RequestParam("schoolGPA") String schoolGPA,
            @RequestParam("collegeName") String collegeName,
            @RequestParam("collegecourse") String collegeCourse,
            @RequestParam("collegeYear") String collegeYear,
            @RequestParam("collegeGPA") String collegeGPA,
            @RequestParam("universityName") String universityName,
            @RequestParam("universityGPA") String universityGPA,
            @RequestParam("coursePursuing") String coursePursuing,
            @RequestParam("universityYear") String universityYear,
            @RequestParam("specialMerits") String specialMerits,
            @RequestParam("bankName") String bankName,
            @RequestParam("annualFamilyIncome") double annualFamilyIncome,
            @RequestParam("bankAccountNumber") String BankAccountNumber,
            @RequestParam("bankIFSCCode") String bankIFSCCode,
            @RequestParam("purposeOfApplying") String purposeofApplying,
            @RequestParam("skillsOrAchievements") String skillsOrAcheivements,
            @RequestParam("scholarshipName") String scholarshipName) {

        // Validate user existence
        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Check if the user has already applied for the same scholarship
        if (scholarshipApplicationService.hasAppliedForScholarship(username, scholarshipId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("You have already applied for this scholarship.");
        }

        // Validate scholarship existence
        ScholarshipEntity scholarship = scholarshipService.getScholarshipById(scholarshipId).orElse(null);
        if (scholarship == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Scholarship not found");
        }

        // Create new scholarship application
        ScholarshipApplication application = new ScholarshipApplication();
        application.setUsername(username);
        application.setFullName(user.getFirstName() + " " + user.getLastName());
        application.setPhoneNumber(user.getPhoneNumber());
        application.setEmail(user.getEmail());
        application.setGender(user.getGender());
        application.setScholarshipEntity(scholarship);

        // Populate additional fields
        application.setCountry(country);
        application.setState(state);
        application.setCity(city);
        application.setMandal(mandal);
        application.setVillage(village);
        application.setAddressLine1(addressLine1);
        application.setStudyType(studyType);
        application.setSchoolName(schoolName);
        application.setClassStudying(classStudying);
        application.setSchoolGPA(schoolGPA);
        application.setCollegeCourse(collegeCourse);
        application.setCollegeName(collegeName);
        application.setCollegeGPA(collegeGPA);
        application.setCollegeYear(collegeYear);
        application.setUniversityName(universityName);
        application.setUniversityGPA(universityGPA);
        application.setCoursePursuing(coursePursuing);
        application.setUniversityYear(universityYear);
        application.setSpecialMerits(specialMerits);
        application.setBankAccountNumber(BankAccountNumber);
        application.setBankIFSCCode(bankIFSCCode);
        application.setBankName(bankName);
        application.setPurposeOfApplying(purposeofApplying);
        application.setSkillsOrAchievements(skillsOrAcheivements);
        application.setScholarshipName(scholarshipName);

        try {
            // Save uploaded files
            if (proofOfCitizenship != null) {
                String path = fileUploadService.storeFile(proofOfCitizenship, username);
                application.setProofOfCitizenship(path);
            }
            if (proofOfStudying != null) {
                String path = fileUploadService.storeFile(proofOfStudying, username);
                application.setProofOfStudying(path);
            }
            if (proofOfFinancial != null) {
                String path = fileUploadService.storeFile(proofOfFinancial, username);
                application.setProofOfFinancial(path);
            }
            if (proofOfMerits != null) {
                String path = fileUploadService.storeFile(proofOfMerits, username);
                application.setProofOfMerits(path);
            }
            // Generate a token (assuming a TokenUtil class is used)
            String token = tokenUtils.generateToken();
            application.setApplicationToken(token);

            // Set application status to 'pending' and save the application in the database
            application.setStatus("pending");
            scholarshipApplicationService.save(application);

            // Send email after successful save
            String subject = "Scholarship Application Submitted Successfully";
            String body = String.format(
                    "Dear %s,\n\nYour application for the scholarship '%s' has been successfully submitted. " +
                    "Here is your application token for reference: %s\n\nThank you for applying!\n\nBest regards,\nScholarship Team",
                    user.getFirstName() + " " + user.getLastName(), scholarshipName, token
            );

            customMailSender.sendEmail(user.getEmail(), subject, body);
            
            

            return ResponseEntity.status(HttpStatus.CREATED).body("Application submitted successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading files: " + e.getMessage());
        }
    }


    @GetMapping("/applications/file")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String filePath) {
        try {
            // Ensure the file path is relative to the 'uploads' folder
            Path path = Paths.get("uploads", filePath); // This will look in the 'uploads' folder
            byte[] data = Files.readAllBytes(path);
            String fileName = path.getFileName().toString();

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + fileName)
                    .body(data);
        } catch (IOException e) {
            // Return 404 if the file is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
