package com.login.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.login.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
        .authorizeHttpRequests(registry -> {
			registry.requestMatchers("/", "/api/login", "/member/adding", "/api/members/save", "/api/members/all", "/api/members/update/{id}","/api/members/delete/{id}").permitAll();
			registry.requestMatchers("/admin/**").hasRole("ADMIN");
			registry.requestMatchers("/user/**").hasRole("USER");
			registry.anyRequest().authenticated();
		})
     
		.formLogin(httpSecurityConfigurer -> {
			httpSecurityConfigurer.loginPage("/login").permitAll();
		});
		return http.build();
    }

    private RequestMatcher myRequestsMatcher() {
        return new AntPathRequestMatcher("/login", "POST");
        // You can customize this matcher based on your specific requirements
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password123").roles("USER");
    }
	
	
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http
//				//.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
//				//creating lambda function
//				.csrf(AbstractHttpConfigurer::disable)
//				.authorizeHttpRequests(registry -> {
//					registry.requestMatchers("/", "/api/login", "/member/adding", "/api/members/save", "/api/members/all", "/api/members/update/{id}","/api/members/delete/{id}").permitAll();
//					registry.requestMatchers("/admin/**").hasRole("ADMIN");
//					registry.requestMatchers("/user/**").hasRole("USER");
//					registry.anyRequest().authenticated();
//				})
//				//.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
//				.formLogin(httpSecurityConfigurer -> {
//					httpSecurityConfigurer.loginPage("/login").permitAll();
//				})
//				.build();
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return myUserDetailsService;
//		
//	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConguration) throws Exception {
		return authenticationConguration.getAuthenticationManager();
	}

}
