package com.example.user_registration.repository;

import com.example.user_registration.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
    // You can add custom query methods if needed
}
