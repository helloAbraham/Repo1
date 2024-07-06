package com.login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.Entity.UserInfo;
import com.login.Service.UserInfoRegistration;

@Configuration
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/auth")
public class MyUserController {
	
	
	@Autowired
	private UserInfoRegistration userInfoReg;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/welcome")
	public String welcomeMsg() {
		return "Welcome to Spring Security World!..";
	}
	

	@GetMapping("/welcome/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public String seeAdmin() {
			return "This page belongs to Admin.";
		}
	
	
	@GetMapping("/genUser")
	@PreAuthorize("hasAuthority('ROLE_USER')")
		public String forAllUsers() {
			return "This page belongs to all users.";
	}
	
		
	
//	@GetMapping("/all")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//		public List<Product>getAllProducts() {
//			return service.getProducts();
//		}

	
//	@GetMapping("/{id}")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public Product getProductById(@PathVariable int id) {
//		return service.getProduct(id);
//	}
	
	
	@PostMapping("/register")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return userInfoReg.addUser(userInfo);
	}
	
	  @PostMapping("/login")
	  public String loginUser() {
	        return "User logged in successfully";
	    }
}
