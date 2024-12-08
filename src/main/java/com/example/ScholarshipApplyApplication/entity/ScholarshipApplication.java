package com.example.ScholarshipApplyApplication.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "scholarship_application")
public class ScholarshipApplication {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Personal Information
    @NotNull
    private String fullName;

    @Column(name = "username", nullable=false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "scholarship_id", referencedColumnName = "id")
    private ScholarshipEntity scholarshipEntity;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String gender;

    // Address Information
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
    private String studyType; // School, College, or University
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
    private String bankName;
    public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	private double annualFamilyIncome;
    private String bankAccountNumber;
    private String bankIFSCCode;

    // Scholarship-Specific Information
    private String purposeOfApplying;
    private String skillsOrAchievements;
    private String scholarshipName;

    // Application Token and Date
    @Column(unique = true)
    private String applicationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;

    // Document Uploads (Paths)
    private String proofOfCitizenship; // Path to proof of citizenship
    private String proofOfStudying;    // Path to proof of studying
    private String proofOfFinancial;  // Path to financial proof
    private String proofOfMerits;     // Path to proof of merits

    // === Constructors, Getters, and Setters ===

    public ScholarshipApplication() {}

    @PrePersist
    public void generateToken() {
        this.applicationToken = UUID.randomUUID().toString();
        if (this.applicationDate == null) {
            this.applicationDate = new Date(); // Set the current date if not provided
        }
    }
    private String status = "Pending"; // Default value

 // Admin Remarks
 private String remarks;

    public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

@Column(name = "proof_of_citizenship_ocr")
private String proofOfCitizenshipOCR;

@Column(name = "proof_of_studying_ocr")
private String proofOfStudyingOCR;

@Column(name = "proof_of_financial_ocr")
private String proofOfFinancialOCR;

@Column(name = "proof_of_merits_ocr")
private String proofOfMeritsOCR;

	public String getProofOfCitizenshipOCR() {
	return proofOfCitizenshipOCR;
}

public void setProofOfCitizenshipOCR(String proofOfCitizenshipOCR) {
	this.proofOfCitizenshipOCR = proofOfCitizenshipOCR;
}

public String getProofOfStudyingOCR() {
	return proofOfStudyingOCR;
}

public void setProofOfStudyingOCR(String proofOfStudyingOCR) {
	this.proofOfStudyingOCR = proofOfStudyingOCR;
}

public String getProofOfFinancialOCR() {
	return proofOfFinancialOCR;
}

public void setProofOfFinancialOCR(String proofOfFinancialOCR) {
	this.proofOfFinancialOCR = proofOfFinancialOCR;
}

public String getProofOfMeritsOCR() {
	return proofOfMeritsOCR;
}

public void setProofOfMeritsOCR(String proofOfMeritsOCR) {
	this.proofOfMeritsOCR = proofOfMeritsOCR;
}

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ScholarshipEntity getScholarshipEntity() {
        return scholarshipEntity;
    }

    public void setScholarshipEntity(ScholarshipEntity scholarshipEntity) {
        this.scholarshipEntity = scholarshipEntity;
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

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
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

    public String getProofOfCitizenship() {
        return proofOfCitizenship;
    }

    public void setProofOfCitizenship(String proofOfCitizenship) {
        this.proofOfCitizenship = proofOfCitizenship;
    }

    public String getProofOfStudying() {
        return proofOfStudying;
    }

    public void setProofOfStudying(String proofOfStudying) {
        this.proofOfStudying = proofOfStudying;
    }

    public String getProofOfFinancial() {
        return proofOfFinancial;
    }

    public void setProofOfFinancial(String proofOfFinancial) {
        this.proofOfFinancial = proofOfFinancial;
    }

    public String getProofOfMerits() {
        return proofOfMerits;
    }

    public void setProofOfMerits(String proofOfMerits) {
        this.proofOfMerits = proofOfMerits;
    }
}
