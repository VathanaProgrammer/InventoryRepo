package com.example.Inventory.Request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String id;
    private String name;
    private String categoryId;
    private String supplierId;
    private BigDecimal price;

    public ProductRequest() {}

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}
