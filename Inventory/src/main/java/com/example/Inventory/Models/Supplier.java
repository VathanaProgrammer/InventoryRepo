package com.example.Inventory.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String contactPerson;

    private String phone;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    // Optional: One supplier supplies many products
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    // Getters and setters
    // ...
}
