package com.MySecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MySecurity.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long>{
	MyUser findByUsername(String username);
}
