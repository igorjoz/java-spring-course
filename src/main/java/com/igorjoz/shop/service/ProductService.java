package com.igorjoz.shop.service;

import com.igorjoz.shop.Product;
import com.igorjoz.shop.Category;
import com.igorjoz.shop.repository.CategoryRepository;
import com.igorjoz.shop.repository.ProductRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Save a product with validation
    @Transactional
    public Product saveProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product with name '" + product.getName() + "' already exists.");
        }
        if (product.getCategory() == null || !categoryRepository.existsById(product.getCategory().getId())) {
            throw new IllegalArgumentException("Product must have a valid category.");
        }
        return productRepository.save(product);
    }

    // Find a product by ID
    public Optional<Product> findProductById(UUID id) {
        return productRepository.findById(id);
    }

    // Find all products
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    // Delete a product
    @Transactional
    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found."));
        productRepository.delete(product);
    }

    // Find products by category ID
    public List<Product> findProductsByCategoryId(UUID categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    // Delegate methods

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findByCategoryId(UUID categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> findByPriceGreaterThan(double price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public long countByCategory(Category category) {
        return productRepository.countByCategory(category);
    }

    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Transactional
    public void deleteByCategory(Category category) {
        productRepository.deleteByCategory(category);
    }
}
