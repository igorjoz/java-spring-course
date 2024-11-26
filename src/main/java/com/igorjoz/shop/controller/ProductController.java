package com.igorjoz.shop.controller;

import com.igorjoz.shop.dto.ProductCreateDTO;
import com.igorjoz.shop.dto.ProductReadDTO;
import com.igorjoz.shop.dto.ProductListDTO;
import com.igorjoz.shop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductReadDTO> createProduct(@RequestBody ProductCreateDTO createDTO) {
        ProductReadDTO product = productService.createProduct(createDTO.getCategoryId(), createDTO);
        return ResponseEntity.ok(product);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductReadDTO> getProduct(@PathVariable UUID id) {
        ProductReadDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<ProductReadDTO> updateProduct(@PathVariable UUID id, @RequestBody ProductCreateDTO updateDTO) {
        ProductReadDTO product = productService.updateProduct(id, updateDTO);
        return ResponseEntity.ok(product);
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // List all products
    @GetMapping
    public ResponseEntity<List<ProductListDTO>> getAllProducts() {
        List<ProductListDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get products by category ID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductListDTO>> getProductsByCategory(@PathVariable UUID categoryId) {
        List<ProductListDTO> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }
}
