package com.example.ScholarshipApplyApplication.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component  // Mark as a Spring Bean to allow injection
public class TokenUtils {

    // Method to generate a unique token
    public String generateToken() {
        return UUID.randomUUID().toString();  // Generates a random UUID as the token
    }
}
