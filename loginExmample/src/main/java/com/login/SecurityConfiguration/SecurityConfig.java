package com.login.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.login.Service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserInfoUserDetailsService();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/api/login", "/api/welcome", "/api/register").permitAll())
//                .authorizeHttpRequests(requests -> requests.requestMatchers("/api/welcome/**")
//                        .authenticated()).formLogin(form -> form.loginPage("/login").permitAll()
//                        	.defaultSuccessUrl("/api/welcome"))
//                
//                .logout(logo -> 
//					logo.logoutUrl("/api/logout")
//					.logoutSuccessUrl("/")
//					.invalidateHttpSession(true)
//					.deleteCookies("JSESSIONID")
//					.permitAll()
//                	)
//                .build();
		
		 http
         .csrf().disable()
         .authorizeRequests()
             .requestMatchers("/api/auth/**").permitAll()
             .anyRequest().authenticated()
         .and()
         .formLogin()
         .and()
         .httpBasic();
     return http.build();
	}
	
	/**
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(AbstractHttpConfigurer::disable)
		.sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(authorizeRequests -> 
			authorizeRequests
			.requestMatchers("/static**", "/registration", "/api/register", "/login").permitAll()
			.requestMatchers("/regis", "/api/auth/**","/dashboard")
			.authenticated()
			.anyRequest().permitAll()
		)
		.formLogin(formLogin -> 
				formLogin.loginPage("/login").permitAll()
				.successForwardUrl("/api/auth/login/idk")
				.defaultSuccessUrl("/dashboard", true)
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
       
       
    }
    */

  

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
	


