package com.MySecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {
	
	//Creating beans using constructor
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	

	public AuthenticationService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}



	//Creating method for check user  from Database the user authenticate or not 
	//Note whatever coding here all Spring Security Library ...
	public void authenticateUser(String username, String password) throws Exception {
		//UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		//if((userDetails != null && userDetails.getUsername().equals(username)) && passwordEncoder.matches(password, userDetails.getPassword()))
		if(userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
		
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		
		
		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			throw new Exception ("Sorry! Invalid username/password");
		}
	}
	

}
