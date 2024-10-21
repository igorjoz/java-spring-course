package com.igorjoz;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Comparable<Product>, Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double price;
    private Category category;

    private Product(ProductBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.category = builder.category;
    }

    // getters -> check how to use project lombok later
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public static class ProductBuilder {
        private String name;
        private double price;
        private Category category;

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            if (price > 0) {
                this.price = price;
            }
            else {
                this.price = 1.0;
            }

            return this;

        }

        public ProductBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", category=" + category.getName() + '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Product product = (Product) other;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }
}

