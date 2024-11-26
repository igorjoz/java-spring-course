package com.igorjoz.shop.service;

import com.igorjoz.shop.Product;
import com.igorjoz.shop.Category;
import com.igorjoz.shop.dto.ProductUpdateDTO;
import com.igorjoz.shop.exception.NotFoundException;
import com.igorjoz.shop.repository.CategoryRepository;
import com.igorjoz.shop.repository.ProductRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igorjoz.shop.dto.ProductCreateDTO;
import com.igorjoz.shop.dto.ProductReadDTO;
import com.igorjoz.shop.dto.ProductListDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    // Create a new product
    public ProductReadDTO createProduct(UUID categoryId, ProductCreateDTO createDTO) throws BadRequestException {
        if (categoryId == null) {
            throw new BadRequestException("Category ID must not be null");
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(createDTO.getName());
        product.setPrice(createDTO.getPrice());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return mapToReadDTO(savedProduct);
    }

    // Read a product by ID
    public ProductReadDTO getProduct(UUID id) {
        return productRepository.findById(id)
                .map(this::mapToReadDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // List all products
    public List<ProductListDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToListDTO)
                .collect(Collectors.toList());
    }

    // Mapping methods
    private ProductReadDTO mapToReadDTO(Product product) {
        ProductReadDTO dto = new ProductReadDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }

    private ProductListDTO mapToListDTO(Product product) {
        ProductListDTO dto = new ProductListDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    @Transactional
    public ProductReadDTO updateProduct(UUID id, ProductUpdateDTO updateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        if (updateDTO.getName() != null) {
            product.setName(updateDTO.getName());
        }
        if (updateDTO.getPrice() != null) {
            product.setPrice(updateDTO.getPrice());
        }
        if (updateDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(updateDTO.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found"));
            product.setCategory(category);
        }
        Product updatedProduct = productRepository.save(product);
        return mapToReadDTO(updatedProduct);
    }

    public List<ProductListDTO> getProductsByCategory(UUID categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream()
                .map(this::mapToListDTO)
                .collect(Collectors.toList());
    }
}
