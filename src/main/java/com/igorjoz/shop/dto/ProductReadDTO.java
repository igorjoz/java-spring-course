package com.igorjoz.shop.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductReadDTO {
    private UUID id;
    private String name;
    private double price;
    private UUID categoryId;
    private String categoryName;
}
