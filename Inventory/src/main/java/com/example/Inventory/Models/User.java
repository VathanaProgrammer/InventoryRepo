package com.example.Inventory.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String password;

    // Getters and Setters
}
