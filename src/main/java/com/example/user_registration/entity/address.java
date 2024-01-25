package com.example.user_registration.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "location_data")
@Data
public class address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false)
    private  String firstname ;
    @Column( nullable = false)
    private String lastname ;
    @Column( nullable = false)
    private String address_name;
//    @Column(nullable = false)
//    private String password;
}
