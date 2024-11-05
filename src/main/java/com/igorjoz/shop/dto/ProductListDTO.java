package com.igorjoz.shop.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductListDTO {
    private UUID id;
    private String name;
    private double price;
}
