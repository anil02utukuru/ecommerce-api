package com.ecommerce.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.ecommerce.demo.model.*;

@Service
public class ProductService {
    private final List<Product> products= new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public ProductService(){
        initializeSampleData();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductById(Long id){
        return products.stream().filter(product->product.getProductId().equals(id)).findFirst();
    }

    private void initializeSampleData(){
        addProduct(new Product(
                null, 
                "Nike Shoes", 
                "Footwear", 
                5999.99, 
                "Shoes that can be used for running, training, sports and all athletic activities", 
                15
            ));

        addProduct(new Product(
                null, 
                "Samsung Refrigerator", 
                "Appliances", 
                18999.99, 
                "A 5 star refrigerator with double door, 5 cooling modes", 
                5
            ));

        addProduct(new Product(
                null, 
                "iPhone 17", 
                "Mobile Phones", 
                79999.99, 
                "Latest version of apple iPhone with iOS 26", 
                100
            ));
    }

    public Product addProduct(Product product){
        if (product.getProductId()==null) {
            product.setProductId(idCounter.getAndIncrement());
        }
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setProductName(updatedProduct.getProductName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            product.setCategory(updatedProduct.getCategory());
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(product -> product.getProductId().equals(id));
    }

    public int getProductCount() {
        return products.size();
    }
}
