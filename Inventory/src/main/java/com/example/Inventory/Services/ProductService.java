package com.example.Inventory.Services;

import com.example.Inventory.DTO.ProductDTO;
import com.example.Inventory.Models.Category;
import com.example.Inventory.Models.Product;
import com.example.Inventory.Models.Supplier;
import com.example.Inventory.Repositories.CategoryRepository;
import com.example.Inventory.Repositories.ProductRepository;
import com.example.Inventory.Repositories.SupplierRepository;
import com.example.Inventory.Request.ProductRequest;
import com.example.Inventory.Responses.ResponseUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public Map<String, Object> createProduct(ProductRequest request) {
        if(request == null){
            return ResponseUtil.error("Product request is null");
        }

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new RuntimeException("Category not found")
        );

        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(
                () -> new RuntimeException("Supplier not found")
        );

        Product product = new Product();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setSupplier(supplier);

        productRepository.save(product);

        return ResponseUtil.success("Product created successfully");
    }

    public List<ProductDTO> filterProducts(String searchText, Long categoryId) {
        List<Product> products = productRepository.findByNameContainingAndCategory(searchText, categoryId);

        // Map Product -> ProductDTO
        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public Map<String, Object> updateProduct(ProductRequest request){
        if(request == null){
            return ResponseUtil.error("Product request is null");
        }

        Optional<Product> productOpt = productRepository.findById(request.getId());

        if(productOpt.isEmpty()){
            return ResponseUtil.error("Product not found");
        }

        Product product = productOpt.get();  // Get the actual Product object

        product.setName(request.getName());
        product.setPrice(request.getPrice());

        productRepository.save(product);

        return ResponseUtil.success("Product updated successfully");
    }

    @Transactional
    public Map<String, Object> deleteProduct(String id){
        if(id == null){
            return ResponseUtil.error("Product request is null");
        }

        Optional<Product> productOpt = productRepository.findById(id);

        if(productOpt.isEmpty()){
            return ResponseUtil.error("Product not found");
        }

        Product product = productOpt.get();

        productRepository.delete(product);

        return ResponseUtil.success("Product deleted successfully");
    }
}
