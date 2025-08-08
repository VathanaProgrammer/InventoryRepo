package com.example.Inventory.Repositories;

import com.example.Inventory.Models.Category;
import com.mysql.cj.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
