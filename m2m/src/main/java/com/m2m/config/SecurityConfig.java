package com.m2m.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService; // <-- Add this import
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService; // <-- 1. Inject this instead

    // 2. Updated Constructor: Only inject the Provider and UserDetailsService
    public SecurityConfig(AuthenticationProvider authenticationProvider, UserDetailsService userDetailsService) {
        this.authenticationProvider = authenticationProvider;
        this.userDetailsService = userDetailsService;
    }

    // 3. Define the filter directly as a Bean to clear the red color loop completely
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService) {
        return new JwtAuthenticationFilter(jwtService, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {
        http
                // 1. Disable CSRF since JWT is stateless
                .csrf(csrf -> csrf.disable())

                // 2. Configure endpoint filtering rules
                .authorizeHttpRequests(auth -> auth
                        // Use explicit HTTP Post matchers to ensure Spring opens up the routes correctly
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/auth/signup", "/api/auth/signin").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        // Lock down everything else
                        .anyRequest().authenticated()
                )

                // 3. Force Stateless Session Management
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
