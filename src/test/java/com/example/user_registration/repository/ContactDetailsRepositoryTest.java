package com.example.user_registration.repository;

import com.example.user_registration.entity.ContactDetails;
import com.example.user_registration.repository.ContactDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class ContactDetailsRepositoryTest {

    @Mock
    private ContactDetailsRepository contactDetailsRepository;

    @Test
    public void testSaveContactDetails() {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setPhoneNumber("1234567890");
        contactDetails.setEmail("test@example.com");

        when(contactDetailsRepository.save(contactDetails)).thenReturn(contactDetails);

        ContactDetails savedContactDetails = contactDetailsRepository.save(contactDetails);

        assertNotNull(savedContactDetails);
        assertEquals("1234567890", savedContactDetails.getPhoneNumber());
        assertEquals("test@example.com", savedContactDetails.getEmail());
    }

    @Test
    public void testFindContactDetailsById() {
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setId(1L);
        contactDetails.setPhoneNumber("1234567890");
        contactDetails.setEmail("test@example.com");

        when(contactDetailsRepository.findById(1L)).thenReturn(Optional.of(contactDetails));

        Optional<ContactDetails> foundContactDetails = contactDetailsRepository.findById(1L);

        assertTrue(foundContactDetails.isPresent());
        assertEquals("1234567890", foundContactDetails.get().getPhoneNumber());

    }
}