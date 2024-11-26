package com.igorjoz.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductUpdateDTO {
    private String name;
    private Double price;
    private UUID categoryId; // Optional
}