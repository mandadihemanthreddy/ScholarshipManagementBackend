package com.example.ScholarshipApplyApplication.dto;

import java.time.LocalDate;

public class UserDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String username;
    private String email;
    private String phoneNumber;
    private String gender;
    private String aadharNumber;

    // Constructor
    public UserDTO(String firstName, String lastName, LocalDate dateOfBirth, String username,
                   String email, String phoneNumber, String gender, String aadharNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.aadharNumber = aadharNumber;
    }

    // Getters and setters
}
