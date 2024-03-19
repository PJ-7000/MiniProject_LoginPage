package com.example.user_registration.service;


import com.example.user_registration.entity.ContactDetails;
import com.example.user_registration.repository.ContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactDetailsService {

    private final ContactDetailsRepository contactDetailsRepository;

    @Autowired
    public ContactDetailsService(ContactDetailsRepository contactDetailsRepository) {
        this.contactDetailsRepository = contactDetailsRepository;
    }

    public List<ContactDetails> getAllContactDetails() {
        return contactDetailsRepository.findAll();
    }

    public ContactDetails getContactDetailsById(Long id) {
        return contactDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ContactDetails not found for id: " + id));
    }

    public ContactDetails createContactDetails(ContactDetails contactDetails) {
        return contactDetailsRepository.save(contactDetails);
    }

    public ContactDetails updateContactDetails(Long id, ContactDetails updatedContactDetails) {
        Optional<ContactDetails> existingContactDetailsOptional = contactDetailsRepository.findById(id);

        if (existingContactDetailsOptional.isPresent()) {
            ContactDetails existingContactDetails = existingContactDetailsOptional.get();
            // Update fields based on your requirements
            existingContactDetails.setEmail(updatedContactDetails.getEmail());
            existingContactDetails.setFirstname(updatedContactDetails.getFirstname());
            existingContactDetails.setPhoneNumber(updatedContactDetails.getPhoneNumber());
            // Update other fields as needed

            return contactDetailsRepository.save(existingContactDetails);
        } else {
            throw new RuntimeException("ContactDetails not found for id: " + id);
        }
    }

    public void deleteContactDetails(Long id) {
        contactDetailsRepository.deleteById(id);
    }
}
