package com.example.ScholarshipApplyApplication.service;

import com.example.ScholarshipApplyApplication.entity.ScholarshipEntity;
import com.example.ScholarshipApplyApplication.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipService {

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    // Fetch all scholarships
    public List<ScholarshipEntity> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    // Fetch a single scholarship by ID (Updated method name)
    public Optional<ScholarshipEntity> getScholarshipById(Long id) {
        return scholarshipRepository.findById(id);
    }

    // Add a new scholarship
    public ScholarshipEntity addScholarship(ScholarshipEntity scholarship) {
        return scholarshipRepository.save(scholarship);
    }

    // Update an existing scholarship
    public ScholarshipEntity updateScholarship(Long id, ScholarshipEntity scholarshipDetails) {
        Optional<ScholarshipEntity> scholarshipOptional = scholarshipRepository.findById(id);

        if (scholarshipOptional.isPresent()) {
            ScholarshipEntity scholarship = scholarshipOptional.get();
            scholarship.setName(scholarshipDetails.getName());
            scholarship.setDescription(scholarshipDetails.getDescription());
            scholarship.setEligibility(scholarshipDetails.getEligibility());
            scholarship.setDeadline(scholarshipDetails.getDeadline());
            scholarship.setType(scholarshipDetails.getType());
            scholarship.setAmount(scholarshipDetails.getAmount());
            return scholarshipRepository.save(scholarship);
        }
        return null;
    }

    // Delete a scholarship by ID
    public void deleteScholarship(Long id) {
        scholarshipRepository.deleteById(id);
    }
}
