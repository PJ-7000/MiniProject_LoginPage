package com.example.user_registration.service;

import com.example.user_registration.entity.ContactDetails;
import com.example.user_registration.repository.ContactDetailsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

        ContactDetails contact1 = new ContactDetails();
        contact1.setId(1L);
        contact1.setFirstname("Alice");

        ContactDetails contact2 = new ContactDetails();
        contact2.setId(2L);
        contact2.setFirstname("Bob");

        List<ContactDetails> mockContactDetailsList = Arrays.asList(contact1, contact2);
        when(contactDetailsRepository.findAll()).thenReturn(mockContactDetailsList);

        List<ContactDetails> result = contactDetailsService.getAllContactDetails();

        assertNotNull(result);
        assertEquals(2, result.size());
    }


    @Test
    public void testGetContactDetailsById() {
        Long id = 1L;
        ContactDetails mockContactDetails = new ContactDetails();
        mockContactDetails.setId(id);
        mockContactDetails.setFirstname("Alice");

        when(contactDetailsRepository.findById(id)).thenReturn(Optional.of(mockContactDetails));

        ContactDetails result = contactDetailsService.getContactDetailsById(id);

        assertNotNull(result);
        assertEquals("Alice", result.getFirstname());
    }


    @Test
    public void testCreateContactDetails() {

        ContactDetails newContactDetails = new ContactDetails();
        newContactDetails.setFirstname("abhi");
        newContactDetails.setPhoneNumber("1234567890");
        newContactDetails.setEmail("abhi@example.com");


        when(contactDetailsRepository.save(any(ContactDetails.class))).thenReturn(newContactDetails);


        ContactDetails createdContactDetails = contactDetailsService.createContactDetails(newContactDetails);

        assertNotNull(createdContactDetails);
        assertNotNull(createdContactDetails.getId()); // Assuming getId() returns the ID of the newly created contact
    }


    @Test
    public void testUpdateContactDetails() {
        Long id = 1L;
        ContactDetails updatedContactDetails = new ContactDetails();
        updatedContactDetails.setId(id);
        updatedContactDetails.setFirstname("UpdatedName");

        when(contactDetailsRepository.save(updatedContactDetails)).thenReturn(updatedContactDetails);

        ContactDetails result = contactDetailsService.updateContactDetails(id, updatedContactDetails);

        assertNotNull(result);
        assertEquals("UpdatedName", result.getFirstname());
    }


    @Test
    public void testDeleteContactDetails() {
        Long id = 1L;

        doNothing().when(contactDetailsRepository).deleteById(id);

        boolean result = contactDetailsService.deleteContactDetails(id);

        assertTrue(result);
    }
}
