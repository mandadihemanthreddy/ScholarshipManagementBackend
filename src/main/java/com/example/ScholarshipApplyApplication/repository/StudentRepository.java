package com.example.ScholarshipApplyApplication.repository;

import com.example.ScholarshipApplyApplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
}