package com.example.user_registration.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Table(name = "contact_details")
@Data
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "contactDetails", cascade = CascadeType.ALL)
    private UserDetails userDetails;
}





