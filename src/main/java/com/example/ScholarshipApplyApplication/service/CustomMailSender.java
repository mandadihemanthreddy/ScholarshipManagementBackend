package com.example.ScholarshipApplyApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CustomMailSender {

    @Autowired
    private JavaMailSender mailSender;
    
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("jfsdprojectmail@gmail.com"); // Your email

            mailSender.send(message);
            System.out.println("Email sent successfully to: " + to);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
