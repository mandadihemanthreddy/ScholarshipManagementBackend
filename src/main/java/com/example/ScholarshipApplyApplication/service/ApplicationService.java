package com.example.ScholarshipApplyApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ScholarshipApplyApplication.entity.Application;
import com.example.ScholarshipApplyApplication.repository.ApplicationRepository;

import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Save a new application
    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    // Edit an existing application
    public Application editApplication(Long id, Application applicationDetails) {
        Optional<Application> existingApplicationOpt = applicationRepository.findById(id);

        if (existingApplicationOpt.isPresent()) {
            Application existingApplication = existingApplicationOpt.get();

            // Update fields as per the new details
            existingApplication.setFullName(applicationDetails.getFullName());
            existingApplication.setPhoneNumber(applicationDetails.getPhoneNumber());
            existingApplication.setEmail(applicationDetails.getEmail());
            existingApplication.setDateOfBirth(applicationDetails.getDateOfBirth());
            existingApplication.setGender(applicationDetails.getGender());
            existingApplication.setCountry(applicationDetails.getCountry());
            existingApplication.setState(applicationDetails.getState());
            existingApplication.setCity(applicationDetails.getCity());
            existingApplication.setMandal(applicationDetails.getMandal());
            existingApplication.setVillage(applicationDetails.getVillage());
            existingApplication.setAddressLine1(applicationDetails.getAddressLine1());
            existingApplication.setAddressLine2(applicationDetails.getAddressLine2());
            existingApplication.setZipCode(applicationDetails.getZipCode());
            existingApplication.setStudyType(applicationDetails.getStudyType());
            existingApplication.setSchoolName(applicationDetails.getSchoolName());
            existingApplication.setClassStudying(applicationDetails.getClassStudying());
            existingApplication.setSchoolGPA(applicationDetails.getSchoolGPA());
            existingApplication.setCollegeName(applicationDetails.getCollegeName());
            existingApplication.setCollegeCourse(applicationDetails.getCollegeCourse());
            existingApplication.setCollegeYear(applicationDetails.getCollegeYear());
            existingApplication.setCollegeGPA(applicationDetails.getCollegeGPA());
            existingApplication.setUniversityName(applicationDetails.getUniversityName());
            existingApplication.setCoursePursuing(applicationDetails.getCoursePursuing());
            existingApplication.setUniversityYear(applicationDetails.getUniversityYear());
            existingApplication.setUniversityGPA(applicationDetails.getUniversityGPA());
            existingApplication.setSpecialMerits(applicationDetails.getSpecialMerits());
            existingApplication.setAnnualFamilyIncome(applicationDetails.getAnnualFamilyIncome());
            existingApplication.setBankAccountNumber(applicationDetails.getBankAccountNumber());
            existingApplication.setBankIFSCCode(applicationDetails.getBankIFSCCode());
            existingApplication.setPurposeOfApplying(applicationDetails.getPurposeOfApplying());
            existingApplication.setSkillsOrAchievements(applicationDetails.getSkillsOrAchievements());

            // Save the updated application
            return applicationRepository.save(existingApplication);
        } else {
            throw new RuntimeException("Application not found with ID: " + id);
        }
    }
}
