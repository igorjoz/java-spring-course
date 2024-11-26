package com.igorjoz.shop.controller;

import com.igorjoz.shop.dto.CategoryCreateDTO;
import com.igorjoz.shop.dto.CategoryReadDTO;
import com.igorjoz.shop.dto.CategoryListDTO;
import com.igorjoz.shop.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<CategoryReadDTO> createCategory(@RequestBody CategoryCreateDTO createDTO) {
        CategoryReadDTO category = categoryService.createCategory(createDTO);
        return ResponseEntity.ok(category);
    }

    // Get a category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryReadDTO> getCategory(@PathVariable UUID id) {
        CategoryReadDTO category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryReadDTO> updateCategory(@PathVariable UUID id, @RequestBody CategoryCreateDTO updateDTO) {
        CategoryReadDTO category = categoryService.updateCategory(id, updateDTO);
        return ResponseEntity.ok(category);
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // List all categories
    @GetMapping
    public ResponseEntity<List<CategoryListDTO>> getAllCategories() {
        List<CategoryListDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
