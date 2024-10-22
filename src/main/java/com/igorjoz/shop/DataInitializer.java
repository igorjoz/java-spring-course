package com.igorjoz.shop;

import com.igorjoz.shop.repository.CategoryRepository;
import com.igorjoz.shop.repository.ProductRepository;
import com.igorjoz.shop.service.CategoryService;
import com.igorjoz.shop.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductService productService;

    public DataInitializer(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           CategoryService categoryService,
                           ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        // Create categories
        Category electronics = new Category();
        electronics.setId(UUID.randomUUID());
        electronics.setName("Electronics");
        electronics.setOrderInShop(1);

        Category clothing = new Category();
        clothing.setId(UUID.randomUUID());
        clothing.setName("Clothing");
        clothing.setOrderInShop(2);

        categoryRepository.saveAll(Arrays.asList(electronics, clothing));

        // Create products
        Product laptop = Product.builder()
                .id(UUID.randomUUID())
                .name("Laptop")
                .price(1200.00)
                .category(electronics)
                .build();

        Product smartphone = Product.builder()
                .id(UUID.randomUUID())
                .name("Smartphone")
                .price(800.00)
                .category(electronics)
                .build();

        Product shirt = Product.builder()
                .id(UUID.randomUUID())
                .name("Shirt")
                .price(50.00)
                .category(clothing)
                .build();

        // Assign products to categories
        electronics.setProducts(Arrays.asList(laptop, smartphone));
        clothing.setProducts(Arrays.asList(shirt));

        // Save products
        productRepository.saveAll(Arrays.asList(laptop, smartphone, shirt));




        // Find products by partial name
        System.out.println("Products containing 'phone':");
        List<Product> phoneProducts = productRepository.findByNameContainingIgnoreCase("phone");
        phoneProducts.forEach(System.out::println);

        // Check if a product exists by name
        boolean laptopExists = productRepository.existsByName("Laptop");
        System.out.println("Does 'Laptop' exist? " + laptopExists);

        // Count products in 'Clothing' category
        clothing = categoryRepository.findByName("Clothing").orElseThrow();
        long clothingProductCount = productRepository.countByCategory(clothing);
        System.out.println("Number of products in Clothing category: " + clothingProductCount);

        // Delete all products in 'Electronics' category
        electronics = categoryService.findByName("Electronics").orElseThrow();
        productService.deleteByCategory(electronics);
        System.out.println("Deleted all products in Electronics category.");
    }
}
