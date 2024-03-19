package com.example.user_registration.service;

import com.example.user_registration.exception.UserAlreadyPresentException;
import com.example.user_registration.model.UserModel;
import com.example.user_registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers() {
        List<UserModel> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        List<UserModel> result = userService.getAllUsers();

        assertEquals(userList, result);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        UserModel user = new UserModel();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<UserModel> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testRegisterUser() {
        UserModel userModel = new UserModel();
        userModel.setUsername("testuser");
        userModel.setPassword("password");

        when(userRepository.existsByUsername(userModel.getUsername())).thenReturn(false);
        when(userRepository.save(userModel)).thenReturn(userModel);

        UserModel result = userService.registerUser(userModel);

        assertEquals(userModel, result);
    }

    @Test
    public void testRegisterUserWithExistingUsername() {
        UserModel userModel = new UserModel();
        userModel.setUsername("existinguser");

        when(userRepository.existsByUsername(userModel.getUsername())).thenReturn(true);

        assertThrows(UserAlreadyPresentException.class, () -> userService.registerUser(userModel));
    }
}