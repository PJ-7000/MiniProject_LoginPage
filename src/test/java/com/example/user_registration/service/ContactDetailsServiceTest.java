package com.example.user_registration.service;


import com.example.user_registration.entity.ContactDetails;
import com.example.user_registration.repository.ContactDetailsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import  org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContactDetailsServiceTest {

    @Mock
    private ContactDetailsRepository contactDetailsRepository;

    @InjectMocks
    private ContactDetailsService contactDetailsService;

    @Test
    public void testGetAllContactDetails() {
        // Mock data setup
        List<ContactDetails> mockContactDetailsList = Arrays.asList(
                new ContactDetails(1L, "Alice"),
                new ContactDetails(2L, "Bob")
        );
        when(contactDetailsRepository.findAll()).thenReturn(mockContactDetailsList);


        List<ContactDetails> result = contactDetailsService.getAllContactDetails();


        assertNotNull(result);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetContactDetailsById() {

        Long id = 1L;
        ContactDetails mockContactDetails = new ContactDetails(id, "Alice");
        when(contactDetailsRepository.findById(id)).thenReturn(Optional.of(mockContactDetails));

        ContactDetails result = contactDetailsService.getContactDetailsById(id);

        assertNotNull(result);

        assertEquals("Alice", result.getName());
    }

    @Test
    public void testCreateContactDetails() {

        ContactDetails newContactDetails = new ContactDetails(null, "Charlie");
        when(contactDetailsRepository.save(any(ContactDetails.class))).thenReturn(new ContactDetails(3L, "Charlie"));

        ContactDetails createdContactDetails = contactDetailsService.createContactDetails(newContactDetails);

        assertNotNull(createdContactDetails);


        assertNotNull(createdContactDetails.getId());
    }
    @Test
    public void testUpdateContactDetails() {

        Long id = 1L;
        ContactDetails updatedContactDetails = new ContactDetails(id, "UpdatedName");
        when(contactDetailsRepository.save(any(ContactDetails.class))).thenReturn(updatedContactDetails);

        ContactDetails result = contactDetailsService.updateContactDetails(id, updatedContactDetails);

        assertNotNull(result);

        assertEquals("UpdatedName", result.getName());
    }

    @Test
    public void testDeleteContactDetails() {

        Long id = 1L;
        doNothing().when(contactDetailsRepository).deleteById(id);


        boolean result = contactDetailsService.deleteContactDetails(id);


        assertTrue(result);
    }
}

