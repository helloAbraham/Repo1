package com.websecure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.websecure.entity.MyEmployee;

@EnableJpaRepositories
@Repository
public interface MyEmployeeRepository extends JpaRepository<MyEmployee, Long>{
	//Optional<MyEmployee> findEmployeeById(Long id);
	//Optional<MyEmployee> addEmp(MyEmployee myEmployee);
}
