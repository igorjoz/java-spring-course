package com.igorjoz.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductCreateDTO {
    private String name;
    private double price;
    private UUID categoryId;
}
