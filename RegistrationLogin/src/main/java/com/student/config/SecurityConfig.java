package com.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
                                .requestMatchers("/static**", "/registration", "/api/v1/student/save", "/api/v1/student/login", "/api/v1/student/dashboardCheck").permitAll()
                                .requestMatchers( "/api/auth/**","/api/v1/student/dashboard", "/api/v1/student/logout").authenticated()
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
                        logo.logoutUrl("/api/v1/student/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll()
                );
        return http.build();

        //.successForwardUrl("/api/auth/login/idk")
    }

}
