package com.example.Inventory.Repositories;

import com.example.Inventory.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
}
