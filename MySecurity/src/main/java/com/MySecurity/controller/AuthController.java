package com.MySecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MySecurity.dto.UserLoginDTO;
import com.MySecurity.dto.UserRegistrationDTO;
import com.MySecurity.service.AuthenticationService;
import com.MySecurity.service.MyUserService;


@Configuration
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auth")
public class AuthController {


	@Autowired
	private MyUserService myUserService;
	
	
	@Autowired
	private AuthenticationService authService;

	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
		if(myUserService.findByUsername(userRegistrationDTO.getUsername()) != null) {
			return ResponseEntity.badRequest().body("User name already taken");
		}
		myUserService.save(userRegistrationDTO);
		return ResponseEntity.ok("User registered successfully!");
	}
	
	//Finally I get correct way to implement the method
	//Note we are using @RequestBody UserLoginDTO parameter to feed the user's input here
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
		authService.authenticateUser(userLoginDTO.getUsername(), userLoginDTO.getPassword());
//		System.out.println("Login Success, " + userLoginDTO.getUsername());
		return ResponseEntity.ok("Welcme! User logged in successfully");
		
		//return new ResponseEntity<>("Welcme! User logged in successfully", HttpStatus.OK);
	}
	
	
	
	//PostMapping trying to get System.out.println output
	//In order to get message postman you have to type "What??? something "
	 @PostMapping("/login/print")
	    public ResponseEntity<String> printMessage(@RequestBody String request) {
	        System.out.println("Yes");  // Print to console
	        return ResponseEntity.ok("Message printed to console");  // Return a ResponseEntity
	    }
	
	
	
	//restricted url
	@GetMapping("/login/idk")
	public String idkInfo() {
		return "You are here becuase you logged in successfully! ";
	}
	
	  @Bean
	    public PasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
