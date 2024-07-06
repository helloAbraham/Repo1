package com.mosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mosque.entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

	MyUser findByUsername(String username);
}
