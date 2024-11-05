package com.igorjoz.shop.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CategoryListDTO {
    private UUID id;
    private String name;
}
