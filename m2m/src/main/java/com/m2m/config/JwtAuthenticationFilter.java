package com.m2m.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails; // <-- Add this import
import org.springframework.security.core.userdetails.UserDetailsService; // <-- Add this import
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService; // <-- 1. ADD THIS FIELD

    // 2. UPDATE CONSTRUCTOR TO INJECT BOTH DEPENDENCIES
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                if (SecurityContextHolder.getContext().getAuthentication() == null && jwtService.isTokenValid(token)) {

                    String email = jwtService.extractEmail(token);

                    // 3. LOAD THE USER FROM THE DATABASE (Crucial step to avoid the internal fallback 403)
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

                    // 4. PASS USER DETAILS AND ACCURATE GRANTED AUTHORITIES
                    var authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities() // <-- Use real user permissions
                    );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (RuntimeException ex) {
                // Invalid tokens do not establish authentication context.
            }
        }

        filterChain.doFilter(request, response);
    }
}
