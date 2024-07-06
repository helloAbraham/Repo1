package com.login.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.login.Entity.UserInfo;
import com.login.Repository.UserInfoRepository;

public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfoOne = userInfoRepo.findByUsername(username);
		
		return userInfoOne.map(UserInfoUserDetails::new)
				.orElseThrow(() ->new UsernameNotFoundException("User Not Found! " + username));
		
	}

}
