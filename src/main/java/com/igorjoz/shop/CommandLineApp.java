package com.igorjoz.shop;

import com.igorjoz.shop.service.CategoryService;
import com.igorjoz.shop.service.ProductService;
import com.igorjoz.shop.Category;
import com.igorjoz.shop.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineApp implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }

//    private final CategoryService categoryService;
//    private final ProductService productService;
//
//    public CommandLineApp(CategoryService categoryService, ProductService productService) {
//        this.categoryService = categoryService;
//        this.productService = productService;
//    }
//
//    @Override
//    public void run(String... args) {
//        Scanner scanner = new Scanner(System.in);
//        boolean running = true;
//
//        while (running) {
//            System.out.println("\nAvailable commands:");
//            System.out.println("1. list-categories");
//            System.out.println("2. list-products");
//            System.out.println("3. add-product");
//            System.out.println("4. delete-product");
//            System.out.println("5. exit");
//            System.out.print("Enter command number: ");
//            String command = scanner.nextLine();
//
//            switch (command) {
//                case "1":
//                    listCategories();
//                    break;
//                case "2":
//                    listProducts();
//                    break;
//                case "3":
//                    addProduct(scanner);
//                    break;
//                case "4":
//                    deleteProduct(scanner);
//                    break;
//                case "5":
//                    running = false;
//                    break;
//                default:
//                    System.out.println("Unknown command.");
//            }
//        }
//
//        scanner.close();
//    }
//
//    private void listCategories() {
//        List<Category> categories = categoryService.findAll();
//        System.out.println("\nCategories:");
//        categories.forEach(category -> System.out.println("- " + category.getName()));
//    }
//
//    private void listProducts() {
//        List<Product> products = productService.findAll();
//        System.out.println("\nProducts:");
//        products.forEach(product -> System.out.println("- " + product.getName() + " (Category: " + product.getCategory().getName() + ", ID: " + product.getId() + ")"));
//    }
//
//    private void addProduct(Scanner scanner) {
//        try {
//            System.out.print("\nEnter product name: ");
//            String name = scanner.nextLine();
//
//            System.out.print("Enter product price: ");
//            double price = Double.parseDouble(scanner.nextLine());
//
//            System.out.print("Enter category name: ");
//            String categoryName = scanner.nextLine();
//
//            Category category = categoryService.findByName(categoryName)
//                    .orElseGet(() -> {
//                        Category newCategory = new Category();
//                        newCategory.setId(UUID.randomUUID());
//                        newCategory.setName(categoryName);
//                        newCategory.setOrderInShop(999);
//                        return categoryService.saveCategory(newCategory);
//                    });
//
//            Product product = Product.builder()
//                    .id(UUID.randomUUID())
//                    .name(name)
//                    .price(price)
//                    .category(category)
//                    .build();
//            productService.saveProduct(product);
//
//            System.out.println("Product added successfully.");
//        } catch (Exception e) {
//            System.out.println("Error adding product: " + e.getMessage());
//        }
//    }
//
//    private void deleteProduct(Scanner scanner) {
//        try {
//            System.out.print("\nEnter product ID (UUID) to delete: ");
//            String idStr = scanner.nextLine();
//            UUID productId = UUID.fromString(idStr);
//
//            productService.deleteProduct(productId);
//            System.out.println("Product deleted successfully.");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error deleting product: Invalid UUID format.");
//        } catch (Exception e) {
//            System.out.println("Error deleting product: " + e.getMessage());
//        }
//    }
}
