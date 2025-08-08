package com.example.Inventory.Controllers;

import com.example.Inventory.Request.SupplierRequest;
import com.example.Inventory.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> getAllSuppliers(){
        Map<String, Object> res = supplierService.getAllSuppliers();
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable String id){
        Map<String, Object> res = supplierService.delete(id);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }

    @PutMapping
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierRequest request) {
        Map<String, Object> res = supplierService.updateSupplier(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }
}
