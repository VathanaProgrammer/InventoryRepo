package com.example.Inventory.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "stock_entries")
public class StockEntry {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('in', 'out')")
    private EntryType type;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public enum EntryType {
        in, out
    }

    @PrePersist
    public void generateId() {
        this.id = "STKENTY-" + UUID.randomUUID().toString().substring(0, 8);
    }

    // Getters and Setters
}

