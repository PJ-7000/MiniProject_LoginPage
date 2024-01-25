package com.example.user_registration.service;

import com.example.user_registration.entity.UserDetails;
import com.example.user_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    //constructor
    //UserServiceImpl class has a constructor that takes a UserRepository as a parameter. This is an example of constructor injection,
    // where the userRepository dependency is injected into the service when it is created.
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserDetails> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /*@Override
    public UserDetails registerUser(UserDetails userDetails) {
        if (userDetails.getUsername() == null || userDetails.getPassword() == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        // Check for username uniqueness
        if (userRepository.existsByUsername(userDetails.getUsername())) {
            throw new IllegalArgumentException("Username must be unique");
        }

        // If the username is unique, save the user
        return saveUser(userDetails);
    }*/

    @Override
    public UserDetails registerUser(UserDetails userDetails) {
        if (userDetails.getUsername() == null || userDetails.getPassword() == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        try {
            // Check for username uniqueness
            if (userRepository.existsByUsername(userDetails.getUsername())) {
                throw new IllegalArgumentException("Username must be unique");
            }

            // If the username is unique, save the user
            return saveUser(userDetails);
        } catch (DataIntegrityViolationException e) {

            throw new IllegalArgumentException("Error registering user. Please check your input data.", e);
        }
    }

    private UserDetails saveUser(UserDetails userDetails) {
        try {
            return userRepository.save(userDetails);
        } catch (DataIntegrityViolationException e) {
            // Catch more specific exception related to database operations
            // You can log the exception for debugging purposes
            // Provide a user-friendly error message
            throw new IllegalArgumentException("Error saving user. Please check your input data.", e);
        }
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
