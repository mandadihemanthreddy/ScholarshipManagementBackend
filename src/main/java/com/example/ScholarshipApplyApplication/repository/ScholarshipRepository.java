package com.example.ScholarshipApplyApplication.repository;

import com.example.ScholarshipApplyApplication.entity.ScholarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScholarshipRepository extends JpaRepository<ScholarshipEntity, Long> {
    // Custom query methods can go here if needed
}
