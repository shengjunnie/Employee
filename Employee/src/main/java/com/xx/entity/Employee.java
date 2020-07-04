package com.xx.entity;

import java.text.ParseException;

import com.ibm.icu.text.SimpleDateFormat;

public class Employee {
	private Integer employeeId;
	
	private String employeeName;
	
	private String employeePassword;
	
	private String idCard;
	
	private Integer positionId;
	
	private Integer deptId;
	
	private String entryTime;
	
	private String phoneNum;
	
	private Integer employeeStatus;
	
	private Integer returnStatus = 1;
	
	
	public Integer getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(Integer employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEntryTime() {
		 if(entryTime == null) {
			 return null;
		 }
			//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
			String str = "";
			try {
				str = sdf.format(sdf.parse(entryTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}

		public void setEntryTime(String entryTime) {
			this.entryTime = entryTime;
		}
	
	public Integer getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeePassword="
				+ employeePassword + ", idCard=" + idCard + ", positionId=" + positionId + ", deptId=" + deptId
				+ ", entryTime=" + entryTime + ", returnStatus=" + returnStatus + "]";
	}
	
	
}
