package com.websecure.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.websecure.dto.EmployeeDTO;
import com.websecure.dto.LoginDTO;
import com.websecure.response.LoginResponse;

public interface EmployeeService {
	
	String addEmployee(EmployeeDTO employeeDTO);
	LoginResponse loginEmployee(LoginDTO loginDTO);
	
}
