package com.example.user_registration.repository;

import com.example.user_registration.entity.UserDetails;
import com.example.user_registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        String username = "testUser";
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(userDetails);

        UserDetails foundUser = userRepository.findByUsername(username);

        assertEquals(username, foundUser.getUsername());
    }

    @Test
    public void testExistsByUsername() {
        String username = "existingUser";

        when(userRepository.existsByUsername(username)).thenReturn(true);

        boolean userExists = userRepository.existsByUsername(username);

        assertTrue(userExists);
    }

    @Test
    public void testFindAll() {
        UserDetails user1 = new UserDetails();
        user1.setUsername("user1");

        UserDetails user2 = new UserDetails();
        user2.setUsername("user2");

        List<UserDetails> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<UserDetails> allUsers = userRepository.findAll();

        assertEquals(2, allUsers.size());
    }
}