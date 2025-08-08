package com.example.Inventory.Request;

import java.math.BigDecimal;

public class SaleItemRequest {
    private String productId;
    private Integer quantity;
    private BigDecimal price; // or BigDecimal

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
