package com.MySecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MySecurity.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long>{
	MyUser findByUsername(String username);
	//Optional<MyUser> findUserByUsername(String username);
}
