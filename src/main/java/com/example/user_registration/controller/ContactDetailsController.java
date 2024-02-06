package com.example.user_registration.controller;
import com.example.user_registration.entity.ContactDetails;
import com.example.user_registration.service.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-details")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    @Autowired
    public ContactDetailsController(ContactDetailsService contactDetailsService) {
        this.contactDetailsService = contactDetailsService;
    }


    @GetMapping
    public List<ContactDetails> getAllContactDetails() {
        return contactDetailsService.getAllContactDetails();
    }

    @GetMapping("/{id}")
    public ContactDetails getContactDetailsById(@PathVariable Long id) {
        return contactDetailsService.getContactDetailsById(id);
    }

    @PostMapping
    public ContactDetails createContactDetails(@RequestBody ContactDetails contactDetails) {
        return contactDetailsService.createContactDetails(contactDetails);
    }

    @PutMapping("/{id}")
    public ContactDetails updateContactDetails(@PathVariable Long id, @RequestBody ContactDetails contactDetails) {
        return contactDetailsService.updateContactDetails(id, contactDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteContactDetails(@PathVariable Long id) {
        contactDetailsService.deleteContactDetails(id);
    }
}