package com.example.ScholarshipApplyApplication.model;

public class ChangePasswordRequest {
    private String Username;
    public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	private String currentPassword;
    private String newPassword;

    // Getters and Setters
    

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
