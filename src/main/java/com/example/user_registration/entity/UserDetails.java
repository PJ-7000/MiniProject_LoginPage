package com.example.user_registration.entity ;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "contact_details_id", nullable = false)
    private ContactDetails contactDetails;
}

