package com.igorjoz.shop.repository;

import com.igorjoz.shop.Product;
import com.igorjoz.shop.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    // JpaRepository already provides:
    // Optional<Product> findById(UUID id);
    // List<Product> findAll();
    // Product save(Product product);
    // void delete(Product product);

    // Custom query methods:

    // Find products by category
    List<Product> findByCategory(Category category);

    // Find products by category ID
    List<Product> findByCategoryId(UUID categoryId);

    // Find product by name
    Optional<Product> findByName(String name);

    // Find products with price greater than specified amount
    List<Product> findByPriceGreaterThan(double price);

    // Find products with price between two values
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Find products where name contains a substring (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String substring);

    // Count products in a category
    long countByCategory(Category category);

    // Check existence of a product by name
    boolean existsByName(String name);

    // Delete products by category
    void deleteByCategory(Category category);
}
