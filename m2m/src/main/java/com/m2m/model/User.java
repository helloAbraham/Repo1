package com.m2m.model; // match your actual package path

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails { // <-- Add this implementation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Add these fields inside your User class definition:
    private String address;

//    @Column(name = "photo_url")
//    private String photoUrl;


    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // --- REQUIRED SPRING SECURITY CONTRACT METHODS ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returns a simple USER role. Adjust this if you use custom roles.
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email; // Your application uses email as the login identity field
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
