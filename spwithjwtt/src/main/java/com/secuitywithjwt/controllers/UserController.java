package com.secuitywithjwt.controllers;

import com.secuitywithjwt.config.JWTService;
import com.secuitywithjwt.entities.User;
import com.secuitywithjwt.request.AuthRequest;
import com.secuitywithjwt.request.UserRequest;
import com.secuitywithjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;


    @PostMapping("/addNew")
    public ResponseEntity<String> addNewUser(@RequestBody UserRequest userEntryDto) {
        try {
            String result = userService.addUser(userEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        throw new UsernameNotFoundException("invalid user details.");
    }

    @GetMapping("/api/v1/welcome")
    public String greet() {
        return "Welcome to Spring Security world!";
    }

}
