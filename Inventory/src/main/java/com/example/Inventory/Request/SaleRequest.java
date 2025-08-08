package com.example.Inventory.Request;

import java.time.LocalDateTime;
import java.util.List;

public class SaleRequest {
    private String userId;  // add getter and setter
    private List<SaleItemRequest> saleItems;
    private String customerName;
    private LocalDateTime saleDate;
    private Double totalAmount;   // <-- add this

    // getters and setters

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<SaleItemRequest> getSaleItems() {
        return saleItems;
    }
    public void setSaleItems(List<SaleItemRequest> saleItems) {
        this.saleItems = saleItems;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
