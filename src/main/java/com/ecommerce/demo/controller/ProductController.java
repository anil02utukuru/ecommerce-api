package com.ecommerce.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.ProductService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController{
    @Autowired
    private  ProductService productService;

    @GetMapping("/") 
    public ResponseEntity<Map<String, Object>> welcome(){
        Map<String,Object> response= new HashMap<>();
        response.put("message", "Welcome to E-Commerce Product API");
        response.put("version", "1.0.0");
        response.put("Total Products", productService.getProductCount());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchProductsList")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product not found");
            error.put("message", "Product with ID " + id + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Map<String, String> errors = validateProduct(product);
        
        if (!errors.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Validation failed");
            response.put("details", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        Optional<Product> updatedProduct = productService.updateProduct(id, product);
        
        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        
        if (deleted) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        // Validate required fields
        Map<String, String> errors = validateProduct(product);
        
        if (!errors.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Validation failed");
            response.put("details", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    private Map<String, String> validateProduct(Product product) {
        Map<String, String> errors = new HashMap<>();
        
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            errors.put("name", "Product name is required");
        }
        
        if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            errors.put("description", "Product description is required");
        }
        
        if (product.getPrice() == null) {
            errors.put("price", "Product price is required");
        } else if (product.getPrice().doubleValue() < 0) {
            errors.put("price", "Product price must be non-negative");
        }
        
        if (product.getStock() == null) {
            errors.put("stock", "Product stock is required");
        } else if (product.getStock() < 0) {
            errors.put("stock", "Product stock must be non-negative");
        }
        
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            errors.put("category", "Product category is required");
        }
        
        return errors;
    }
}
