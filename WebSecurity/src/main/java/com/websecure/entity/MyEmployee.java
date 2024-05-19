package com.websecure.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "MyEmployee")
public class MyEmployee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	private String name;
	private String phoneNumber;
	private String employeeCode;
	
	public MyEmployee() {
		
	}

	

	public MyEmployee(Long id, String name, String phoneNumber, String employeeCode) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.employeeCode = employeeCode;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getEmployeeCode() {
		return employeeCode;
	}



	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}



	@Override
	public String toString() {
		return "MyEmployee [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
}
