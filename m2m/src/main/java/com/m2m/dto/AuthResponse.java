package com.m2m.dto;


public record AuthResponse(
        String message,
        String token,
        Long userId,
        String name,
        String email
)
{
}
