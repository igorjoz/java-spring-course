package com.igorjoz.shop.repository;

import com.igorjoz.shop.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    // JpaRepository provides:
    // Optional<Category> findById(UUID id);
    // List<Category> findAll();
    // Category save(Category category);
    // void delete(Category category);

    Optional<Category> findByName(String name);
    List<Category> findByOrderInShop(int orderInShop);
    List<Category> findByNameContainingIgnoreCase(String substring);
    long countByName(String name);
    boolean existsByName(String name);
}
