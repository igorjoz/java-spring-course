package com.igorjoz;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Category implements Comparable<Category>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;
    private final int orderInShop;
    private final List<Product> products;

    public Category(String name, int orderInShop, List<Product> products) {
        this.name = name;
        this.orderInShop = orderInShop;
        this.products = products;
    }

    // getters -> check how to use project lombok later
    public String getName() {
        return name;
    }

    public int getOrderInShop() {
        return orderInShop;
    }

    public List<Product> getProducts() {
        return products;
    }

    // equals method for comparison based on 'name'
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Category category = (Category) other;
        return Objects.equals(name, category.name);
    }

    // hashing based on 'name'
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // natural ordering for categories, based on 'name'
    @Override
    public int compareTo(Category other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Category{name='" + name + "', products=" + products + '}';
    }
}
