package com.example.Inventory.Repositories;

import com.example.Inventory.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {


    @Query("SELECT p FROM Product p WHERE " +
            "(:searchText IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :searchText, '%'))) AND " +
            "(:categoryId IS NULL OR p.category.id = :categoryId)")
    List<Product> findByNameContainingAndCategory(
            @Param("searchText") String searchText,
            @Param("categoryId") Long categoryId);
}
