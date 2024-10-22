package com.igorjoz.shop.repository;

import com.igorjoz.shop.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    // JpaRepository already provides:
    // Optional<Category> findById(UUID id);
    // List<Category> findAll();
    // Category save(Category category);
    // void delete(Category category);

    // Custom query methods:

    // Find category by name
    Optional<Category> findByName(String name);

    // Find categories by orderInShop
    List<Category> findByOrderInShop(int orderInShop);

    // Find categories where name contains a substring (case-insensitive)
    List<Category> findByNameContainingIgnoreCase(String substring);

    // Count categories with a specific name
    long countByName(String name);

    // Check existence of a category by name
    boolean existsByName(String name);
}
