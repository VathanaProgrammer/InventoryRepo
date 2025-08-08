package com.example.Inventory.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @PrePersist
    public void generateId() {
        this.id = "CATE-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}