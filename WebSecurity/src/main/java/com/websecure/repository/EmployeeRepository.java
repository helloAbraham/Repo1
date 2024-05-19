package com.websecure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.websecure.entity.Employee;
@EnableJpaRepositories
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Optional<Employee> findByEmailAndPassword(String email, String password);
	Employee findByEmail(String email);
	
	
}
