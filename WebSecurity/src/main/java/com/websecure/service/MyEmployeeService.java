package com.websecure.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websecure.entity.MyEmployee;
import com.websecure.repository.MyEmployeeRepository;

@Service
public class MyEmployeeService {
	
	@Autowired
	private MyEmployeeRepository myEmployeeRepo;
	
	
	public MyEmployee addEmp(MyEmployee myEmployee) {
		myEmployee.setEmployeeCode(UUID.randomUUID().toString());
		return myEmployeeRepo.save(myEmployee);
	}

}
