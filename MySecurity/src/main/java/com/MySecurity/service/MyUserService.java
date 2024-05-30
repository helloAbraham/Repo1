package com.MySecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.MySecurity.dto.UserLoginDTO;
import com.MySecurity.dto.UserRegistrationDTO;
import com.MySecurity.entity.MyUser;
import com.MySecurity.repository.MyUserRepository;

@Service
public class MyUserService {
	
	@Autowired
	private MyUserRepository myUserRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
//	private AuthenticationManager authenticationManager;
//
//	
//	
//	@Autowired
//	public MyUserService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
//		this.authenticationManager = authenticationManager;
//		this.passwordEncoder = passwordEncoder;
//	}
	
	public void save(UserRegistrationDTO registration) {
		MyUser userone = new MyUser();
		userone.setUsername(registration.getUsername());
		userone.setPassword(passwordEncoder.encode(registration.getPassword()));
		userone.setEnabled(true);
		userone.setRoles(List.of("ROLE_USER"));
		myUserRepo.save(userone);
	}
	
	//Since we are going to use Front-End user login
	public MyUser findByUsername(String username) {
		return myUserRepo.findByUsername(username);
	}
	

}
