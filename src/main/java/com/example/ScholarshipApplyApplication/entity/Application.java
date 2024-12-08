package com.example.ScholarshipApplyApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.UUID;
import java.util.Date;

@Entity
@Table(name = "scholarship_applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String fullName;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String dateOfBirth;

    @NotNull
    private String gender;

    // Address fields
    @NotNull
    private String country = "India";
    private String state;
    private String city;
    private String mandal;
    private String village;
    @NotNull
    private String addressLine1;
    private String addressLine2;
    private String zipCode;

    // Academic Information
    @NotNull
    private String studyType; // School, College, University

    private String schoolName;
    private String classStudying;
    private String schoolGPA;

    private String collegeName;
    private String collegeCourse;
    private String collegeYear;
    private String collegeGPA;

    private String universityName;
    private String coursePursuing;
    private String universityYear;
    private String universityGPA;
    private String specialMerits;

    // Financial Information
    private double annualFamilyIncome;
    private String bankAccountNumber;
    private String bankIFSCCode;

    // Scholarship-Specific Information
    private String purposeOfApplying;
    private String skillsOrAchievements;

    // Token for Application
    private String applicationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;

    // Method to generate a unique application token before persisting
    @PrePersist
    public void generateToken() {
        this.applicationToken = UUID.randomUUID().toString();
        if (this.applicationDate == null) {
            this.applicationDate = new Date(); // Set current date if not provided
        }
    }
    
    public String getScholarshipName() {
		return scholarshipName;
	}

	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}

	@Column(name = "scholarship_name")
    private String scholarshipName;


    // Getters and Setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassStudying() {
        return classStudying;
    }

    public void setClassStudying(String classStudying) {
        this.classStudying = classStudying;
    }

    public String getSchoolGPA() {
        return schoolGPA;
    }

    public void setSchoolGPA(String schoolGPA) {
        this.schoolGPA = schoolGPA;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeCourse() {
        return collegeCourse;
    }

    public void setCollegeCourse(String collegeCourse) {
        this.collegeCourse = collegeCourse;
    }

    public String getCollegeYear() {
        return collegeYear;
    }

    public void setCollegeYear(String collegeYear) {
        this.collegeYear = collegeYear;
    }

    public String getCollegeGPA() {
        return collegeGPA;
    }

    public void setCollegeGPA(String collegeGPA) {
        this.collegeGPA = collegeGPA;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCoursePursuing() {
        return coursePursuing;
    }

    public void setCoursePursuing(String coursePursuing) {
        this.coursePursuing = coursePursuing;
    }

    public String getUniversityYear() {
        return universityYear;
    }

    public void setUniversityYear(String universityYear) {
        this.universityYear = universityYear;
    }

    public String getUniversityGPA() {
        return universityGPA;
    }

    public void setUniversityGPA(String universityGPA) {
        this.universityGPA = universityGPA;
    }

    public String getSpecialMerits() {
        return specialMerits;
    }

    public void setSpecialMerits(String specialMerits) {
        this.specialMerits = specialMerits;
    }

    public double getAnnualFamilyIncome() {
        return annualFamilyIncome;
    }

    public void setAnnualFamilyIncome(double annualFamilyIncome) {
        this.annualFamilyIncome = annualFamilyIncome;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankIFSCCode() {
        return bankIFSCCode;
    }

    public void setBankIFSCCode(String bankIFSCCode) {
        this.bankIFSCCode = bankIFSCCode;
    }

    public String getPurposeOfApplying() {
        return purposeOfApplying;
    }

    public void setPurposeOfApplying(String purposeOfApplying) {
        this.purposeOfApplying = purposeOfApplying;
    }

    public String getSkillsOrAchievements() {
        return skillsOrAchievements;
    }

    public void setSkillsOrAchievements(String skillsOrAchievements) {
        this.skillsOrAchievements = skillsOrAchievements;
    }

    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
}
