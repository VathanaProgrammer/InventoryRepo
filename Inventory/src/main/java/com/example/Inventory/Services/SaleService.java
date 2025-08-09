package com.example.Inventory.Services;
import com.example.Inventory.Models.Product;
import com.example.Inventory.Models.Sale;
import com.example.Inventory.Models.SaleItem;
import com.example.Inventory.Models.User;
import com.example.Inventory.Repositories.ProductRepository;
import com.example.Inventory.Repositories.SaleRepository;
import com.example.Inventory.Repositories.UserRepository;
import com.example.Inventory.Request.SaleItemRequest;
import com.example.Inventory.Request.SaleRequest;
import com.example.Inventory.Request.StockEntryRequest;
import com.example.Inventory.Responses.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private com.example.Inventory.Service.StockService stockService;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Map<String, Object> createSale(SaleRequest request) {
        if (request == null || request.getSaleItems() == null || request.getSaleItems().isEmpty()) {
            return ResponseUtil.error("Sale items are required");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));

        Sale sale = new Sale();
        sale.setCustomerName(request.getCustomerName());
        sale.setSaleDate(request.getSaleDate() != null ? request.getSaleDate() : LocalDateTime.now());
        sale.setUser(user);

        double totalAmount = 0;
        List<SaleItem> saleItems = new ArrayList<>();

        for (SaleItemRequest itemReq : request.getSaleItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemReq.getProductId()));

            // Check stock availability before reducing
            if (product.getQuantity() < itemReq.getQuantity()) {
                return ResponseUtil.error("Insufficient stock for product: " + product.getName());
            }

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setQuantity(itemReq.getQuantity());
            saleItem.setPrice(itemReq.getPrice().doubleValue());

            totalAmount += saleItem.getQuantity() * saleItem.getPrice();
            saleItems.add(saleItem);

            // Reduce product stock
            product.setQuantity(product.getQuantity() - itemReq.getQuantity());
            productRepository.save(product);
        }

        sale.setSaleItems(saleItems);
        sale.setTotalAmount(totalAmount);

        // Save sale and sale items
        Sale savedSale = saleRepository.save(sale);

        // Create stock entries now that sale id exists
        for (SaleItem saleItem : saleItems) {
            StockEntryRequest stockEntryRequest = new StockEntryRequest();
            stockEntryRequest.setProductId(saleItem.getProduct().getId());
            stockEntryRequest.setUserId(request.getUserId());
            stockEntryRequest.setType("out");
            stockEntryRequest.setQuantity(saleItem.getQuantity());
            stockEntryRequest.setPrice(BigDecimal.valueOf(saleItem.getPrice()));
            stockEntryRequest.setNote("Sale ID: " + savedSale.getId());

            stockService.createStockEntry(stockEntryRequest);
        }

        return ResponseUtil.success("Sale created successfully");
    }

}
