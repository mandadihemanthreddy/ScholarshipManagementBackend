package com.example.ScholarshipApplyApplication.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ScholarshipApplyApplication.entity.ScholarshipApplication;

@Repository
public interface ScholarshipApplicationRepository extends JpaRepository<ScholarshipApplication, Long> {
    List<ScholarshipApplication> findByUsername(String username);
    Optional<ScholarshipApplication> findByApplicationToken(String applicationToken);
    boolean existsByUsernameAndScholarshipEntity_Id(String username, Long scholarshipId);

}
