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

    // JpaRepository provides:
    // Optional<Product> findById(UUID id);
    // List<Product> findAll();
    // Product save(Product product);
    // void delete(Product product);

    List<Product> findByCategory(Category category);
    List<Product> findByCategoryId(UUID categoryId);
    Optional<Product> findByName(String name);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByNameContainingIgnoreCase(String substring);
    long countByCategory(Category category);
    boolean existsByName(String name);
    void deleteByCategory(Category category);
}
