package com.websecure.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.websecure.dto.EmployeeDTO;
import com.websecure.dto.LoginDTO;
import com.websecure.entity.Employee;
import com.websecure.repository.EmployeeRepository;
import com.websecure.response.LoginResponse;

@Service
public class EmployeeIMPL implements EmployeeService {
	

	private EmployeeRepository employeeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	EmployeeDTO employeeDTO;

	@Override
	public String addEmployee(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee(
				employeeDTO.getEmployeename(),
				employeeDTO.getEmail(),
				this.passwordEncoder.encode(employeeDTO.getPassword())
				);
		
		employeeRepo.save(employee);
		return employee.getEmployeename();
	}

	@Override
	public LoginResponse loginEmployee(LoginDTO loginDTO) {
		
		Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
		
		if(employee1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = employee1.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if(isPwdRight) {
				Optional<Employee> emp = employeeRepo.findByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
					if(emp.isPresent()) {
						return new LoginResponse("Login Success", true);
					} else {
						return new LoginResponse("Login Failed", false);
					}
			} else {
				return new LoginResponse("Password Not Match", false);
			}
		} else {
			return new LoginResponse("Email not exits", false);
		}
	}

	
}
