package com.websecure.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id", nullable= false, length = 255)
	private int employeeId;
	
	@Column(name="employee_name", nullable=false, length = 255)
	private String employeename;
	
	@Column(nullable = false, name="email", length = 255)
	private String email;
	
	@Column(name="password", length = 255)
	private String password;
	
	public Employee() {
		
	}

	public Employee(String employeename, String email, String password) {
		
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
		return "Employee [employeeId=" + ", employeeName=" + employeename + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
	
	
	

}
