package com.example.ScholarshipApplyApplication.repository;

import com.example.ScholarshipApplyApplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByPhoneNumber(String phoneNumber); // This is no longer used for login
}


