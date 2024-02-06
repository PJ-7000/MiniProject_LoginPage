package com.example.user_registration.controller;

import com.example.user_registration.entity.UserDetails;
import com.example.user_registration.model.UserModel;
import com.example.user_registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//used to generate a constructor that includes all final fields of a class as parameters .
//Lombok annotation
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        List<UserDetails> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                //ifPresent
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetails> registerUser(@RequestBody UserModel userModel) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(userModel.getUsername());
        userDetails.setPassword(userModel.getPassword());
        // Add any other fields or validations as needed

        UserDetails savedUser = userService.registerUser(userDetails);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

