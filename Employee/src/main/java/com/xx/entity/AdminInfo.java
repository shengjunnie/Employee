package com.xx.entity;

public class AdminInfo {
    private Integer adminId;

    private String adminName;

    private String adminPassword;
    
    private Integer returnStatus;

    
    public Integer getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}

	public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }
}