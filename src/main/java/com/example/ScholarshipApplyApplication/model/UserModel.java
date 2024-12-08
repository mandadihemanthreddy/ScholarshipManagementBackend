package com.example.ScholarshipApplyApplication.model;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserModel {

    @NotBlank(message = "First Name is required")
    private String firstName;

	@NotBlank(message = "Last Name is required")
    private String lastName;

    @NotNull(message = "Date of Birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Pattern(regexp = "^\\d{12}$", message = "Aadhar number should be 12 digits")
    private String aadharNumber;

    private String role;

    // Constructors, Getters and Setters
    public UserModel() {}

    public UserModel(String username, String email, String role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    // Add Getters and Setters for all fields
}
