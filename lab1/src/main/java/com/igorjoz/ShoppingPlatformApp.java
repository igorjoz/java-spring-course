package com.igorjoz;

import java.util.Arrays;
import java.util.List;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class ShoppingPlatformApp {
    public static void main(String[] args) throws Exception {
        // 2.1 create categories and products using builder pattern; assign category to products
        Category electronics = new Category("Electronics", 1, null);
        Product laptop = new Product.ProductBuilder()
                .setName("Laptop")
                .setPrice(1200.00)
                .setCategory(electronics)
                .build();
        Product keyboard = new Product.ProductBuilder()
                .setName("Keyboard")
                .setPrice(30.00)
                .setCategory(electronics)
                .build();
        Product smartphone = new Product.ProductBuilder()
                .setName("Smartphone")
                .setPrice(800.00)
                .setCategory(electronics)
                .build();

        Category clothing = new Category("Clothing", 2, null);
        Product shirt = new Product.ProductBuilder()
                .setName("Shirt")
                .setPrice(50.00)
                .setCategory(clothing)
                .build();
        Product pants = new Product.ProductBuilder()
                .setName("Pants")
                .setPrice(70.00)
                .setCategory(clothing)
                .build();

        // 2.2 assign products to their categories - two-way relationship; one category has many products
        electronics = new Category(electronics.getName(), 1, Arrays.asList(laptop, keyboard, smartphone));
        clothing = new Category(clothing.getName(), 2, Arrays.asList(shirt, pants));

        // 2.3 print categories and products in original order
        List<Category> categories = Arrays.asList(electronics, clothing);

        System.out.println("---Original order of categories and products---");
        categories.forEach(category -> {
            System.out.println("Category: " + category.getName());
            category.getProducts().forEach(product ->
                    System.out.println("\tProduct: " + product.getName() + ", Price: " + product.getPrice())
            );
        });

        // 3.1 single stream API pipeline: create Set collection
        Set<Product> productsSet = categories.stream()
                .flatMap(category -> category.getProducts().stream())
                .collect(Collectors.toSet());

        // 3.2 print productsSet elements
        System.out.println("\n---Print productsSet---");
        productsSet.stream()
                .forEach(product -> System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice()));

        // 4. filter, sort, and print products
        System.out.println("\n---Filtered and sorted products: price > 500, sort by name---");
        productsSet.stream()
                .filter(product -> product.getPrice() > 500.00)  // filter by price > 500
                .sorted(Comparator.comparing(Product::getName))  // sort by name
                .forEach(product -> System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice()));

        // 5. transform to DTO, sort by natural order (name) and print
        System.out.println("\n---Product DTOs sorted by name---");
        List<ProductDto> productDtos = productsSet.stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice(), product.getCategory().getName()))
                .sorted(Comparator.comparing(ProductDto::getName))  // sort by name (natural order)
                .collect(Collectors.toList());

        productDtos.forEach(dto -> System.out.println("ProductDto: " + dto.getName() + ", Price: " + dto.getPrice() + ", Category: " + dto.getCategoryName()));

        // 6.1 serialization: store and read categories with products
        serializeCategories(categories, "categories.ser");
        List<Category> deserializedCategories = deserializeCategories("categories.ser");

        // 6.2 print deserialized categories and products
        System.out.println("\n---Deserialized categories and products---");
        deserializedCategories.forEach(category -> {
            System.out.println("Category: " + category.getName());
            category.getProducts().forEach(product ->
                    System.out.println("  Product: " + product.getName() + ", Price: " + product.getPrice())
            );
        });

        // 7. parallel pipeline using custom thread pool with simulated workload
        ForkJoinPool customThreadPool = new ForkJoinPool(2);  // Custom thread pool
        customThreadPool.submit(() -> {
            categories.parallelStream().forEach(category -> {
                category.getProducts().forEach(product -> {
                    try {
                        System.out.println("Processing: " + product.getName());
                        Thread.sleep(1000);  // simulate workload with delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
        }).get();  // wait for completion

        customThreadPool.shutdown();  // close the thread pool
    }

    // 6. serialization method
    public static void serializeCategories(List<Category> categories, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(categories);
        }
    }

    // 6. deserialization method
    @SuppressWarnings("unchecked")
    public static List<Category> deserializeCategories(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Category>) ois.readObject();
        }
    }
}


