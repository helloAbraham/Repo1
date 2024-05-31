package com.MySecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.MySecurity.service.CustomeUserDetailsService;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	@Autowired
	private CustomeUserDetailsService myUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
//	@Bean
//	public AuthenticationManagerBuilder authenticationManager(HttpSecurity http) throws Exception {
//		return http.getSharedObject(AuthenticationManagerBuilder.class);
//	}
	
	
//	public void confgure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//	}
	
	//new 
	 @Bean
	  public AuthenticationManager authenticationManager(
	      AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	  }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager auth) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(authorizeRequests -> 
				authorizeRequests
				.requestMatchers("/registration", "/api/auth/register").permitAll()
				.anyRequest().permitAll()
			)
			.formLogin(formLogin -> 
					formLogin.loginPage("/login")
					.permitAll()
					)
			.logout(logo -> 
			logo.logoutUrl("/api/auth/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
			);
		return http.build();
			
	}
	
	
}
