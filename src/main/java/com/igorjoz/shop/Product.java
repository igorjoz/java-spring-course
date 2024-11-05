package com.igorjoz.shop;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "category")
public class Product implements Comparable<Product>, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    // Bidirectional relationship
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Product(UUID id, String name, double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price > 0 ? price : 1.0;
        this.category = category;
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }
}
