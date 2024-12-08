package com.example.ScholarshipApplyApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.ScholarshipApplyApplication.entity.Application;
import com.example.ScholarshipApplyApplication.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@Validated
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Submit a new application
    @PostMapping
    public ResponseEntity<Application> submitApplication(@Valid @RequestBody Application application) {
        Application savedApplication = applicationService.saveApplication(application);
        return ResponseEntity.ok(savedApplication);
    }

    // Edit an existing application
    @PutMapping("/{id}")
    public ResponseEntity<Application> editApplication(
            @PathVariable Long id,
            @Valid @RequestBody Application applicationDetails) {
        Application updatedApplication = applicationService.editApplication(id, applicationDetails);
        return ResponseEntity.ok(updatedApplication);
    }
}
