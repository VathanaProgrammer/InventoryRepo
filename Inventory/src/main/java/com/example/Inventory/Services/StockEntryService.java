package com.example.Inventory.Services;

import com.example.Inventory.Models.Product;
import com.example.Inventory.Models.StockEntry;
import com.example.Inventory.Models.User;
import com.example.Inventory.Repositories.ProductRepository;
import com.example.Inventory.Repositories.StockEntryRepository;
import com.example.Inventory.Repositories.UserRepository;
import com.example.Inventory.Request.StockEntryRequest;
import com.example.Inventory.Responses.ResponseUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.Map;
import java.util.Optional;

@Service
public class StockEntryService {

    @Autowired
    private StockEntryRepository stockEntryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Map<String, Object> createStockEntry(StockEntryRequest request) throws IllegalArgumentException {
        Optional<Product> productOpt = productRepository.findById(request.getProductId());
        Optional<User> userOpt = userRepository.findById(request.getUserId());

        if (productOpt.isEmpty()) {
            throw new IllegalArgumentException("Product not found with id: " + request.getProductId());
        }
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found with id: " + request.getUserId());
        }
        if (request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (request.getType() == null ||
                (!request.getType().equalsIgnoreCase("in") && !request.getType().equalsIgnoreCase("out"))) {
            throw new IllegalArgumentException("Type must be 'in' or 'out'");
        }

        StockEntry stockEntry = new StockEntry();
        stockEntry.setProduct(productOpt.get());
        stockEntry.setUser(userOpt.get());
        stockEntry.setQuantity(request.getQuantity());
        stockEntry.setNote(request.getNote());
        stockEntry.setType(StockEntry.EntryType.valueOf(request.getType().toLowerCase()));

        // Update product quantity accordingly:
        Product product = productOpt.get();
        int currentQty = product.getQuantity() != null ? product.getQuantity() : 0;
        if (stockEntry.getType() == StockEntry.EntryType.in) {
            product.setQuantity(currentQty + stockEntry.getQuantity());
        } else { // 'out'
            int newQty = currentQty - stockEntry.getQuantity();
            if (newQty < 0) {
                throw new IllegalArgumentException("Insufficient stock for product " + product.getId());
            }
            product.setQuantity(newQty);
        }
        productRepository.save(product); // update product qty

        stockEntryRepository.save(stockEntry);

        return ResponseUtil.success("Stock entry created successfully");
    }
}
