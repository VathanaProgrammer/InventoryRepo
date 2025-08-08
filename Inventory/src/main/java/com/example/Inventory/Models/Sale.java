package com.example.Inventory.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    private String id;

    private String customerName;

    private LocalDateTime saleDate;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> saleItems;

    @PrePersist
    public void generateId() {
        this.id = "SLE-" + UUID.randomUUID().toString().substring(0, 8);
    }
    // Getters and setters
    // ...
}
