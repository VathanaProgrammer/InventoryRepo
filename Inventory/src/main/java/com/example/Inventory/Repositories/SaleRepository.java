package com.example.Inventory.Repositories;

import com.example.Inventory.Models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, String> {
}
