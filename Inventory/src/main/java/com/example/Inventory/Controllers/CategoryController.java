package com.example.Inventory.Controllers;

import com.example.Inventory.Models.Category;
import com.example.Inventory.Request.CategoryRequest;
import com.example.Inventory.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
        Map<String, Object> res = categoryService.createCategory(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody CategoryRequest request) {
        Map<String, Object> res = categoryService.updateCategory(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCategory(@RequestBody CategoryRequest request){
        Map<String, Object> res = categoryService.deleteCategory(request);
        return ResponseEntity.status(res.get("success").equals(true) ? 200 : 400).body(res);
    }
}
