package com.example.user_registration.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}


