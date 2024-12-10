package com.example.ScholarshipApplyApplication.controller;

import com.example.ScholarshipApplyApplication.entity.UserEntity;
import com.example.ScholarshipApplyApplication.entity.ScholarshipEntity;
import com.example.ScholarshipApplyApplication.model.ChangePasswordRequest;
import com.example.ScholarshipApplyApplication.model.UserModel;
import com.example.ScholarshipApplyApplication.service.UserService;
import com.example.ScholarshipApplyApplication.service.OCRService;
import com.example.ScholarshipApplyApplication.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://scholarship-management-mu.vercel.app/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScholarshipService scholarshipService;
    
    @Autowired
    private  OCRService ocrService;

    // Sign up a new user
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserModel userModel) {
        if (userService.findByUsername(userModel.getUsername()) != null) {
            return new ResponseEntity<>("Username already taken", HttpStatus.CONFLICT);
        }

        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userModel.getFirstName());
        newUser.setLastName(userModel.getLastName());
        newUser.setDateOfBirth(userModel.getDateOfBirth());
        newUser.setUsername(userModel.getUsername());
        newUser.setPassword(userModel.getPassword()); // No password hashing here
        newUser.setEmail(userModel.getEmail());
        newUser.setPhoneNumber(userModel.getPhoneNumber());
        newUser.setGender(userModel.getGender());
        newUser.setAadharNumber(userModel.getAadharNumber());
        newUser.setRole("USER");

        userService.save(newUser);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    // Log in an existing user using username and password
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        UserEntity user = userService.authenticateByUsername(username, password);

        if (user != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", user.getRole());
            response.put("username", user.getUsername());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }
    }



    // Fetch user profile
    @PutMapping("/profile/update")
    public ResponseEntity<String> updateUserProfile(@RequestParam String username, @RequestBody UserModel updatedUser) {
        UserEntity user = userService.findByUsername(username);

        if (user != null) {
            // Update user properties
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setAadharNumber(updatedUser.getAadharNumber());
            user.setGender(updatedUser.getGender());

            userService.save(user); // Save updated user details

            return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/profile/{username}")
    public ResponseEntity<UserEntity> getUserProfile(@PathVariable String username) {
        System.out.println("Fetching profile for username: " + username); // Log the username for debugging
        UserEntity user = userService.findByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Fetch list of scholarships
    @GetMapping("/scholarships")
    public ResponseEntity<List<ScholarshipEntity>> getScholarships() {
        List<ScholarshipEntity> scholarships = scholarshipService.getAllScholarships();
        return scholarships.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                                      : new ResponseEntity<>(scholarships, HttpStatus.OK);
    }

    // Change user password
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        boolean isPasswordChanged = userService.changePasswordByUsername(
                request.getUsername(),
                request.getCurrentPassword(),
                request.getNewPassword()
        );

        if (isPasswordChanged) {
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/upload-document")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        System.out.println("Received request to upload document.");
        try {
            String extractedText = ocrService.extractTextFromImage(file);
            return new ResponseEntity<>(extractedText, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to process the document.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
