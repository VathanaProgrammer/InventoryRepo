package com.example.Inventory.Services;

import com.example.Inventory.Models.Category;
import com.example.Inventory.Models.Product;
import com.example.Inventory.Models.Supplier;
import com.example.Inventory.Repositories.SupplierRepository;
import com.example.Inventory.Request.ProductRequest;
import com.example.Inventory.Request.SupplierRequest;
import com.example.Inventory.Responses.ResponseUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public Map<String, Object> createSupplier(SupplierRequest request) {
        if(request == null){
            return ResponseUtil.error("Supplier request is null");
        }

        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());

        supplierRepository.save(supplier);

        return ResponseUtil.success("Product created successfully");
    }

    public Map<String, Object> getAllSuppliers(){
        return ResponseUtil.success("All Suppliers", supplierRepository.findAll());
    }

    public Map<String, Object> delete(String id){
        if(id == null){
            return ResponseUtil.error("Supplier request is null");
        }
        Optional<Supplier> supplierOpt = supplierRepository.findById(id);
        Supplier supplier = supplierOpt.get();
        supplierRepository.delete(supplier);
        return ResponseUtil.success("Supplier deleted successfully");
    }

    public Map<String, Object> updateSupplier(SupplierRequest request){
        if(request == null){
            return ResponseUtil.error("Supplier request is null");
        }
        Optional<Supplier> supplierOpt = supplierRepository.findById(request.getId());
        Supplier supplier = supplierOpt.get();
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplierRepository.save(supplier);
        return ResponseUtil.success("Supplier updated successfully");
    }
}
