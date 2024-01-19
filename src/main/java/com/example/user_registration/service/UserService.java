package com.example.user_registration.service;

import com.example.user_registration.entity.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDetails> getAllUsers();

    Optional<UserDetails> getUserById(Long id);

    UserDetails registerUser(UserDetails userDetails);

    void deleteUser(Long id);
}
