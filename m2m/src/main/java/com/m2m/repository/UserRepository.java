package com.m2m.repository;

import com.m2m.model.User; // Ensure this matches your User model path
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used by ApplicationConfig / JwtService for token lookups
    Optional<User> findByEmail(String email);

    // ADD THIS EXACT LINE TO FIX THE COMPILATION ERROR:
    boolean existsByEmail(String email);
}
