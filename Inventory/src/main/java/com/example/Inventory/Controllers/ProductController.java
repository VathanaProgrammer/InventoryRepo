package com.example.Inventory.Controllers;

import com.example.Inventory.DTO.ProductDTO;
import com.example.Inventory.Request.ProductRequest;
import com.example.Inventory.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        Map<String, Object> res = productService.createProduct(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }

    // product with search text and category
    @GetMapping("/filter")
    public List<ProductDTO> filterProducts(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Long categoryId) {
        return productService.filterProducts(searchText, categoryId);
    }

    //get all products
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return filterProducts(null, null);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest request) {
        Map<String, Object> res = productService.updateProduct(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        Map<String, Object> res = productService.deleteProduct(id);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }


}
