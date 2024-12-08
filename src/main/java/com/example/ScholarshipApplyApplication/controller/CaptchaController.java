package com.example.ScholarshipApplyApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ScholarshipApplyApplication.dto.Captcha;
import com.example.ScholarshipApplyApplication.service.CaptchaService;

import org.springframework.http.ResponseEntity;

@RestController
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    // Endpoint to generate a math CAPTCHA
    @GetMapping("/api/captcha")
    public Captcha getCaptcha() {
        return captchaService.generateCaptcha();
    }

    // Validate CAPTCHA response (user's answer)
    @PostMapping("/api/captcha/validate")
    public ResponseEntity<?> validateCaptcha(@RequestBody Captcha userCaptcha) {
        // Assuming you have stored the correct answer somewhere, like in a session
        int correctAnswer = captchaService.getStoredAnswer(); // Get the correct answer from the service
        int userAnswer = userCaptcha.getAnswer(); // The user-submitted answer

        if (userAnswer == correctAnswer) {
            return ResponseEntity.ok("CAPTCHA validated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Incorrect CAPTCHA answer.");
        }
    }


}
