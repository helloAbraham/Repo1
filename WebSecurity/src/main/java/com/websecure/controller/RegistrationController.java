package com.websecure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.websecure.entity.MyUser;
import com.websecure.repository.MyUserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegistrationController {
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@CrossOrigin(origins = "http://localhost:8080") 
	@PostMapping("/register/user")
	public MyUser createUser(@RequestBody MyUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return myUserRepository.save(user);
	}
	
	@GetMapping("/")
	public String home() {
		return "Welcome Home |^^| ";
	}
	@CrossOrigin(origins = "http://localhost:8080") 
	@GetMapping("/login")
	public String handleLogin() {
		return "custome_login";
	}

}
