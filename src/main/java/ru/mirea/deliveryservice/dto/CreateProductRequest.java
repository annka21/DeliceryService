package ru.mirea.deliveryservice.dto;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String title;
    private String description;
    private Double price;
    private Double discount;
    private Integer availableAmount;
}
