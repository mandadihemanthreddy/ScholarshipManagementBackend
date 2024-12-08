package com.example.ScholarshipApplyApplication.service;

import com.example.ScholarshipApplyApplication.entity.ScholarshipApplication;
import com.example.ScholarshipApplyApplication.repository.ScholarshipApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipApplicationService {

    @Autowired
    private ScholarshipApplicationRepository scholarshipApplicationRepository;

    // Save or update an application
    public void save(ScholarshipApplication scholarshipApplication) {
        scholarshipApplicationRepository.save(scholarshipApplication);
    }

    // Get all applications
    public List<ScholarshipApplication> getAllApplications() {
        return scholarshipApplicationRepository.findAll();
    }

    // Get application by ID
    public ScholarshipApplication getApplicationById(Long id) {
        return scholarshipApplicationRepository.findById(id).orElse(null);
    }

    // Check if the user has already applied for the same scholarship
    public boolean hasAppliedForScholarship(String username, Long scholarshipId) {
        return scholarshipApplicationRepository.existsByUsernameAndScholarshipEntity_Id(username, scholarshipId);
    }

    // This function is no longer needed because we no longer care about the status of previous applications
    public boolean canApplyForNewScholarship(String username) {
        return true; // User can apply to any scholarship, no status check
    }
}

