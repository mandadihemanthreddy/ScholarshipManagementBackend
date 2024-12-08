package com.example.ScholarshipApplyApplication.controller;

import com.example.ScholarshipApplyApplication.entity.UserEntity;
import com.example.ScholarshipApplyApplication.model.UserModel;
import com.example.ScholarshipApplyApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
        userService.createUser(userModel);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        boolean updated = userService.updateUser(id, userModel);
        return updated ? new ResponseEntity<>("User updated successfully", HttpStatus.OK)
                       : new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? new ResponseEntity<>("User deleted successfully", HttpStatus.OK)
                       : new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
