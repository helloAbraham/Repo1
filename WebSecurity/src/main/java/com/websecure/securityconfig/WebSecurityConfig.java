package com.websecure.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.websecure.service.MyUserDetailService;

//import com.websecure.service.MyUserDetailService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				//.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
				//creating lambda function
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/home", "/register/**", "/member/adding", "/api/members/save", "/api/members/all", "/api/members/update/{id}","/api/members/delete/{id}").permitAll();
					registry.requestMatchers("/admin/**").hasRole("ADMIN");
					registry.requestMatchers("/user/**").hasRole("USER");
					registry.anyRequest().authenticated();
				})
				//.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
				.formLogin(httpSecurityConfigurer -> {
					httpSecurityConfigurer.loginPage("/login").permitAll();
				})
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 //this below one I learn from Spring Security Documentation using thymleaf
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
		http
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/", "home", "api/employee/save").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()
				)
				.logout((iamdone) -> iamdone.permitAll());
		return http.build();
	}
	*/
	
	/**
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails myUser = User.withDefaultPasswordEncoder()
								 .username("Abraham2024")
								 .password("Abraham123hell0")
								 .roles("USER")
								 .build();
		
		
		return new InMemoryUserDetailsManager(myUser);
	}
	*/
	
	
	
	
}
