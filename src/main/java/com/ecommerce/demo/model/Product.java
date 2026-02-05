package com.ecommerce.demo.model;

public class Product {
    Long productId;
    String productName;
    String category;
    Double price;
    String description;
    Integer Stock;
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getStock() {
        return Stock;
    }
    public void setStock(int stock) {
        Stock = stock;
    }

    public Product(Long productId, String productName, String category, Double price, String description,
            int stock) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        Stock = stock;
    }
    
    
    
}
