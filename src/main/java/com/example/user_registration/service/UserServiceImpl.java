package com.example.user_registration.service;

import com.example.user_registration.entity.UserDetails;
import com.example.user_registration.repository.UserRepository;
import com.example.user_registration.exception.UserAlreadyPresentException;
import com.example.user_registration.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserDetails> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found")));
    }

    @Override
    public UserDetails registerUser(UserDetails userDetails) {
        validateUserDetails(userDetails);

        try {
            return saveUser(userDetails);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Error registering user. Please check your input data.", e);
        }
    }

    private void validateUserDetails(UserDetails userDetails) {
        if (userDetails.getUsername() == null || userDetails.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        if (userRepository.existsByUsername(userDetails.getUsername())) {
            throw new UserAlreadyPresentException("User with username '" + userDetails.getUsername() + "' already exists");
        }

        if (userDetails.getPassword() == null || userDetails.getPassword().length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters long");
        }
    }

    private UserDetails saveUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
