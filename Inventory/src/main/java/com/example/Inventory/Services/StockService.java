package com.example.Inventory.Service;

import com.example.Inventory.Models.*;
import com.example.Inventory.Repositories.ProductRepository;
import com.example.Inventory.Repositories.StockEntryRepository;
import com.example.Inventory.Repositories.*;
import com.example.Inventory.Request.StockEntryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockEntryRepository stockEntryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void createStockEntry(StockEntryRequest request) {
        StockEntry stockEntry = new StockEntry();

        // Fetch Product entity by ID
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found: " + request.getProductId());
        }
        stockEntry.setProduct(productOpt.get());

        // Fetch User entity by ID
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + request.getUserId());
        }
        stockEntry.setUser(userOpt.get());

        // Set other fields
        stockEntry.setQuantity(request.getQuantity());
        stockEntry.setNote(request.getNote());

        // Convert type String to Enum safely
        try {
            StockEntry.EntryType entryType = StockEntry.EntryType.valueOf(request.getType());
            stockEntry.setType(entryType);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid stock entry type: " + request.getType());
        }
        

        stockEntryRepository.save(stockEntry);
    }
}
