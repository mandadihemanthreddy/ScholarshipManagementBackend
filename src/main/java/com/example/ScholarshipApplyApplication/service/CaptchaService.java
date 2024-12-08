package com.example.ScholarshipApplyApplication.service;

import org.springframework.stereotype.Service;
import com.example.ScholarshipApplyApplication.dto.Captcha;

@Service
public class CaptchaService {

    private int correctAnswer;

    public Captcha generateCaptcha() {
        // Generate a random math question (e.g., 3 + 7)
        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        correctAnswer = num1 + num2;

        // Return the question and correct answer (the answer will be validated later)
        String question = num1 + " + " + num2; // Generate the question as a String
        return new Captcha(question, num1, num2, correctAnswer); // Updated constructor
    }

    public int getStoredAnswer() {
        return correctAnswer;
    }
}
