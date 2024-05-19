package com.websecure.dto;




public class EmployeeDTO {



	private String employeename;
	private String email;
	private String password;
	
	public EmployeeDTO() {
	}

	public EmployeeDTO(String employeename, String email, String password) {
	
		this.employeename = employeename;
		this.email = email;
		this.password = password;
	}

	
	

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" +  ", employeeName=" + employeename + ", email=" + email
				+ ", password=" + password + "]";
	}

	
	

}
