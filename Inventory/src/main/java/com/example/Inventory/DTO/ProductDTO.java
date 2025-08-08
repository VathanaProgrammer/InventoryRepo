package com.example.Inventory.DTO;

import com.example.Inventory.Models.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String categoryId;
    private String categoryName; // optional, for displaying category
    private String supplierId;
    private String supplierName; // optional, for displaying supplier
    private BigDecimal price;
    private LocalDateTime createdAt;

    public ProductDTO() {}
    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
        this.supplierId = product.getSupplier().getId();
        this.supplierName = product.getSupplier().getName();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
    }
}
