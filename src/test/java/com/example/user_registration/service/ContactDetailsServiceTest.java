package com.example.user_registration.service;


import com.example.user_registration.entity.ContactDetails;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ContactDetailsServiceTest {

    @Mock
    private ContactDetailsRepository contactDetailsRepository;

    @InjectMocks
    private ContactDetailsService contactDetailsService;

    @Test
    public void testGetAllContactDetails() {

    }

    @Test
    public void testGetContactDetailsById() {

    }

    @Test
    public void testCreateContactDetails() {
        ContactDetails newContactDetails = new ContactDetails();
        newContactDetails.setId(1L);
        newContactDetails.setName("John Doe");


        Mockito.when(contactDetailsRepository.save(newContactDetails)).thenReturn(newContactDetails);

        ContactDetails createdContactDetails = contactDetailsService.createContactDetails(newContactDetails);


    }

    @Test
    public void testUpdateContactDetails() {
        Long id = 1L;
        ContactDetails updatedContactDetails = new ContactDetails();
        updatedContactDetails.setId(id);
        updatedContactDetails.setName("Jane Smith");


        Mockito.when(contactDetailsRepository.findById(id)).thenReturn(Optional.of(new ContactDetails()));
        Mockito.when(contactDetailsRepository.save(updatedContactDetails)).thenReturn(updatedContactDetails);

        ContactDetails result = contactDetailsService.updateContactDetails(id, updatedContactDetails);


    }

    @Test
    public void testDeleteContactDetails() {
        Long id = 1L;


        Mockito.when(contactDetailsRepository.existsById(id)).thenReturn(true);

        contactDetailsService.deleteContactDetails(id);

    }
}


