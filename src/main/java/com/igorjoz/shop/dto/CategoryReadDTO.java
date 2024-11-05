package com.igorjoz.shop.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CategoryReadDTO {
    private UUID id;
    private String name;
    private int orderInShop;
}
