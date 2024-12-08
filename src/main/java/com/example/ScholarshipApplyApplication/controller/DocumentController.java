package com.example.ScholarshipApplyApplication.controller;

import com.example.ScholarshipApplyApplication.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class DocumentController {

    private final OCRService ocrService;

    @Autowired
    public DocumentController(OCRService ocrService) {
        this.ocrService = ocrService;
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
