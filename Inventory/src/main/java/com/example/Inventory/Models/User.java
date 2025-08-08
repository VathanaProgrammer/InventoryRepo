package com.example.Inventory.Models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String password;

    @PrePersist
    public void generateId() {
        this.id = "USR-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
