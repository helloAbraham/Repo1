package com.m2m.controller;

import com.m2m.dto.UserDto;
import com.m2m.model.User;
import com.m2m.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allows communication with your Vue frontend
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        // userDetails.getUsername() contains the email extracted from the JWT token
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User profile context not found"));

        UserDto profileData = UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress() != null ? user.getAddress() : "No address specified yet")
//                .photoUrl(user.getPhotoUrl() != null ? user.getPhotoUrl() : "https://placeholder.com")
                .build();

        return ResponseEntity.ok(profileData);
    }
}

