package com.example.ScholarshipApplyApplication.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class OCRService {

    public String extractTextFromImage(MultipartFile file) {
        ITesseract tesseract = new Tesseract();

        // Ensure the path to tessdata folder is correct
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng"); // Specify the language as English

        try {
            // Convert the MultipartFile to a File object
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);

            // Extract text from the image
            String extractedText = tesseract.doOCR(convFile);

            // Clean up temporary file
            convFile.delete();

            return extractedText;

        } catch (TesseractException | IOException e) {
            e.printStackTrace();
            return "Error: Unable to process the file.";
        }
    }
}
