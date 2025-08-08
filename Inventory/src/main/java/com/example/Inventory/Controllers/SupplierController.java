package com.example.Inventory.Controllers;

import com.example.Inventory.Request.SupplierRequest;
import com.example.Inventory.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<?> createSupplier(@RequestBody SupplierRequest request) {
       Map<String, Object> res = supplierService.createSupplier(request);
       return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }
}
