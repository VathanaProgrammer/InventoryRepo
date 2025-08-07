package com.example.Inventory.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_entries")
public class StockEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    // Getters and Setters
}

