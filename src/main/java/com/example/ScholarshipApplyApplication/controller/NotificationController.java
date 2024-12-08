package com.example.ScholarshipApplyApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class NotificationController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/send")
    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("primefriends119@gmail.com");  // Replace with the actual recipient email
            message.setSubject("Test Email Subject");
            message.setText("This is a test email sent directly from the controller.");
            message.setFrom("jfsdprojectmail@gmail.com");  // Replace with your actual email address

            // Send email
            mailSender.send(message);
            System.out.println("Email sent successfully.");

            return "Email sent successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email: " + e.getMessage();
        }
    }
}
