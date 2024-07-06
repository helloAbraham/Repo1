package com.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.Entity.UserInfo;
import com.login.Repository.UserInfoRepository;

@Service
public class UserInfoRegistration {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserInfoRepository userInfoRepo;
	
	
	
	public String addUser(UserInfo userInfo ) {
		//for password encode
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepo.save(userInfo);
		return "You added the user successfully.";
	}
	

}
