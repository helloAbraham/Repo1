package com.login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.Entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
	
	MyUser findByUsername(String username);

}
