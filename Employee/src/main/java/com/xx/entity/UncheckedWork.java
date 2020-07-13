package com.xx.entity;

import java.text.ParseException;

import com.ibm.icu.text.SimpleDateFormat;

public class UncheckedWork {
	private Integer uncheckedId;
	
	private Integer employeeId;
	
	private Integer employeeName;
	
	private String submissionTime;
	
	private String cargo;
	
	private String workType;
	
	private double quantity;
	
	private Integer workId;

	public Integer getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(Integer employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getUncheckedId() {
		return uncheckedId;
	}

	public void setUncheckedId(Integer uncheckedId) {
		this.uncheckedId = uncheckedId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getSubmissionTime() {
		 if(submissionTime == null) {
			 return null;
		 }
			//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			//SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
			String str = "";
			try {
				str = sdf.format(sdf.parse(submissionTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}


	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	
}
