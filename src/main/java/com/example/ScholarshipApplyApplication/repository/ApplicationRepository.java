package com.example.ScholarshipApplyApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ScholarshipApplyApplication.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    // Additional queries can be added if needed
}
