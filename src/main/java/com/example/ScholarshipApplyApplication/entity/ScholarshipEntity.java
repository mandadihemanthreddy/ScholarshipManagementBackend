package com.example.ScholarshipApplyApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "scholarship_entity")
public class ScholarshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key, make sure it's Long (BIGINT in SQL)

    private String name;
    private String description;
    private String eligibility;
    private String deadline;
    private String type;
    private Double amount;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	// Constructors, getters, and setters
    public ScholarshipEntity() {}

    public ScholarshipEntity(String name, String description, String eligibility, String deadline, String type, Double amount) {
        this.name = name;
        this.description = description;
        this.eligibility = eligibility;
        this.deadline = deadline;
        this.type = type;
        this.amount = amount;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Other getters and setters...
}
