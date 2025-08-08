package com.example.Inventory.Controllers;

import com.example.Inventory.Request.SaleRequest;
import com.example.Inventory.Responses.ResponseUtil;
import com.example.Inventory.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleRequest request) {
        saleService.createSale(request);
        return ResponseEntity.ok(ResponseUtil.success("Sale created successfully"));
    }

}
