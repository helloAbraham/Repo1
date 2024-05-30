package com.MySecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MySecurity.entity.MyUser;
import com.MySecurity.repository.MyUserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

		@Autowired
		private MyUserRepository myUserRepo;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MyUser userone = myUserRepo.findByUsername(username);
		
		if(userone == null) {
			throw new UsernameNotFoundException("Sorry! User not found " + username );
		}
		return new CustomUserDetails(userone);
	}

}
