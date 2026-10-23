package com.m2m.service;


import com.m2m.dto.AuthResponse;
import com.m2m.dto.SigninRequest;
import com.m2m.dto.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request);

    AuthResponse signin(SigninRequest request);
}
