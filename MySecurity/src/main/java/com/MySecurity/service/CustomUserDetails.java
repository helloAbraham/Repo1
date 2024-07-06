package com.MySecurity.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.MySecurity.entity.MyUser;

@Component
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private MyUser myUser;
	
	public CustomUserDetails() {
		
	}
	
	public CustomUserDetails(MyUser myUser) {
		this.myUser = myUser;
	}

	//Method returning a Collection of type that extends GrantedAuthority
	//<? extends GrantedAuthority>: This is a bounded wildcard. 
	//It means that the collection can hold any type that is a subclass of GrantedAutority, 
	//including GrantedAuthority itself.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return myUser.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}
	
	@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return myUser.getUsername();
		}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return myUser.getPassword();
	}

	
	@Override
	public boolean isEnabled() {
		return myUser.isEnabled();
	}

}
