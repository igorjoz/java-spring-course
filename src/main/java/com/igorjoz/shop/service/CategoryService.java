package com.igorjoz.shop.service;

import com.igorjoz.shop.Category;
import com.igorjoz.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Transactional
    public Category saveCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category with name '" + category.getName() + "' already exists.");
        }
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));
        categoryRepository.delete(category);
    }

    public Optional<Category> findCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
    public List<Category> findByOrderInShop(int orderInShop) {
        return categoryRepository.findByOrderInShop(orderInShop);
    }
    public List<Category> findByNameContainingIgnoreCase(String substring) {
        return categoryRepository.findByNameContainingIgnoreCase(substring);
    }
    public long countByName(String name) {
        return categoryRepository.countByName(name);
    }
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
