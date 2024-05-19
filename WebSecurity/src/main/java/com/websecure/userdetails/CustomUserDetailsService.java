//package com.websecure.userdetails;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.websecure.entity.User;
//import com.websecure.repository.UserRepository;
//
//public class CustomUserDetailsService implements UserDetailsService{
//	
//	private UserRepository userRepo;
//	
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		User myUser = userRepo.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("Sorry! the user you are looking for not found: " + username));
//		return org.springframework.security.core.userdetails.User
//				.withUsername(myUser.getUsername())
//				.password(myUser.getPassword())
//				.build();
//	}
//
//}
