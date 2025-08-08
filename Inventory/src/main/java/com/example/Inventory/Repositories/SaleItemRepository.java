package com.example.Inventory.Repositories;

import com.example.Inventory.Models.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, String> {
}
