package edu.uark.models.api;

import org.apache.commons.lang3.StringUtils;

public class EmployeeSignIn {
	private String employeeId;
	public String getEmployeeId() {
		return this.employeeId;
	}
	public EmployeeSignIn setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
		return this;
	}
	private String password;
	public String getPassword() {
		return this.password;
	}
	public EmployeeSignIn setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public EmployeeSignIn() {
		this.password = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
	}
}
