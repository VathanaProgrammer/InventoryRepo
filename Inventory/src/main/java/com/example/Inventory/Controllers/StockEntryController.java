package com.example.Inventory.Controllers;

import com.example.Inventory.Models.StockEntry;
import com.example.Inventory.Request.StockEntryRequest;
import com.example.Inventory.Services.StockEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stock_entries")
public class StockEntryController {

    @Autowired
    private StockEntryService stockEntryService;
    
    @PostMapping
    public ResponseEntity<?> addStockEntry(@RequestBody StockEntryRequest request) {
            Map<String, Object> res = stockEntryService.createStockEntry(request);
            return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }
}
