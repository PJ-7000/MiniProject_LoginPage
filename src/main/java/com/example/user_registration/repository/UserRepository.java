package com.example.user_registration.repository;

import com.example.user_registration.entity.UserDetails;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByUsername(String username);
    boolean existsByUsername(String username);
    List<UserDetails> findAll();
}
