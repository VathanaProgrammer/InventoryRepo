package com.example.Inventory.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "sale_items")
public class SaleItem {

    @Id
    private String id;

    private Long quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void generateId() {
        this.id = "SLI-" + UUID.randomUUID().toString().substring(0, 8);
    }
    // Getters and setters
    // ...
}
