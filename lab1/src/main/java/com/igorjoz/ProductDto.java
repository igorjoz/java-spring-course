package com.igorjoz;

public class ProductDto {
    private String name;
    private double price;
    private String categoryName;

    // Constructor
    public ProductDto(String name, double price, String categoryName) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // Text Representation
    @Override
    public String toString() {
        return "ProductDto{name='" + name + "', price=" + price + ", categoryName='" + categoryName + "'}";
    }
}
