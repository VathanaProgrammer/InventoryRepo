package com.example.Inventory.Repositories;

import com.example.Inventory.Models.StockEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockEntryRepository extends JpaRepository<StockEntry, String> {
}
