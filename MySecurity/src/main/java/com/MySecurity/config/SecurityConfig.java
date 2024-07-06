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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.MySecurity.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService myUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	
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
			.sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorizeRequests -> 
				authorizeRequests
				.requestMatchers("/static**", "/registration", "/api/auth/register", "/api/auth/login", "/api/auth/login/print").permitAll()
				.requestMatchers("/regis", "/api/auth/**","/dashboard").authenticated()
				.anyRequest().permitAll()
			)
			.formLogin(formLogin -> 
					formLogin.loginPage("/login")
//					.usernameParameter("username")
//					.passwordParameter("password")
					.successForwardUrl("/")
					.defaultSuccessUrl("/", true)
					.failureUrl("/login?error=true")
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
	
		//.successForwardUrl("/api/auth/login/idk")
	}
	
}
