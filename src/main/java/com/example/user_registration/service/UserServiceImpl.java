package com.example.user_registration.service;

import com.example.user_registration.entity.UserDetails;
import com.example.user_registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
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

    @Override
    public UserDetails registerUser(UserDetails userDetails) {
        // You can add additional logic/validation before saving the user
        return userRepository.save(userDetails);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
