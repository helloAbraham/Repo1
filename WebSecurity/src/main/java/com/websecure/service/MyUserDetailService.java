package com.websecure.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.websecure.entity.MyUser;
import com.websecure.repository.MyUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private MyUserRepository myUserRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MyUser> newUser = myUserRepo.findByUsername(username);
		if(newUser.isPresent()) {
			var userObj = newUser.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
		} else {
			throw new UsernameNotFoundException(username);
		}
	}
	
	private String[] getRoles(MyUser user) {
		if(user.getRole() == null) {
			return new String[] {"USER"};
		}
		return user.getRole().split(",");
	}

}



