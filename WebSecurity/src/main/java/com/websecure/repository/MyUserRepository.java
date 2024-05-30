package com.websecure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websecure.entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long>{
	
	Optional<MyUser> findByUsername(String username);
	//MyUser findByUsername(String username);

}
