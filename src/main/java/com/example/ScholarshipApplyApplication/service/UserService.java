package com.example.ScholarshipApplyApplication.service;

import com.example.ScholarshipApplyApplication.entity.UserEntity;
import com.example.ScholarshipApplyApplication.model.UserModel;
import com.example.ScholarshipApplyApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Find user by username
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Save user
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    // Authenticate user by username and password
    public UserEntity authenticateByUsername(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // Change password based on username
    public boolean changePasswordByUsername(String username, String currentPassword, String newPassword) {
        UserEntity user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(currentPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    // Get all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    public void createUser(UserModel userModel) {
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userModel.getFirstName());
        newUser.setLastName(userModel.getLastName());
        newUser.setDateOfBirth(userModel.getDateOfBirth());
        newUser.setUsername(userModel.getUsername());
        newUser.setPassword(userModel.getPassword());
        newUser.setEmail(userModel.getEmail());
        newUser.setPhoneNumber(userModel.getPhoneNumber());
        newUser.setGender(userModel.getGender());
        newUser.setAadharNumber(userModel.getAadharNumber());
        newUser.setRole(userModel.getRole());
        userRepository.save(newUser);
    }

    // Update an existing user
    public boolean updateUser(Long id, UserModel updatedUser) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setGender(updatedUser.getGender());
            user.setAadharNumber(updatedUser.getAadharNumber());
            user.setRole(updatedUser.getRole());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // Delete a user by ID
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<String> getAllUserEmails() {
        return userRepository.findAll().stream()
            .map(UserEntity::getEmail)
            .collect(Collectors.toList());
    }

}
