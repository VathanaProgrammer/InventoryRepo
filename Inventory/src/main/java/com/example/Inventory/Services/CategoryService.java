package com.example.Inventory.Services;

import com.example.Inventory.Models.Category;
import com.example.Inventory.Repositories.CategoryRepository;
import com.example.Inventory.Request.CategoryRequest;
import com.example.Inventory.Responses.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Map<String, Object> createCategory(CategoryRequest request) {
        if(request == null){
            return ResponseUtil.error("Category request is null");
        }

        Category category = new Category();
        category.setName(request.getName());

        categoryRepository.save(category);

        return ResponseUtil.success("Category created successfully", category);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Map<String, Object> updateCategory(CategoryRequest request){
        if(request == null){
            return ResponseUtil.error("Category request is null");
        }
        Optional<Category> categoryOpt = categoryRepository.findById(request.getId());
        Category category = categoryOpt.get();

        category.setName(request.getName());

        categoryRepository.save(category);

        return ResponseUtil.success("Category updated successfully", category);
    }

    public Map<String, Object> deleteCategory(String id){
        if(id == null){
            return ResponseUtil.error("Category request is null");
        }

        Optional<Category> categoryOpt = categoryRepository.findById(id);
        Category category = categoryOpt.get();

        categoryRepository.delete(category);

        return ResponseUtil.success("Category deleted successfully");
    }
}
