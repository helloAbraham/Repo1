package com.login.DataInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
@Configuration
public class DataInitializer {
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
	
//	@Bean
//	CommandLineRunner init(MyUserRepository myUserRepo) {
//		return args -> {
//			//Create a test user
//			MyUser user = new MyUser();
//			user.setUsername("Parker123");
//			user.setPassword(passwordEncoder.encode("Parker456"));
//			myUserRepo.save(user);
//		};
//	}

}
