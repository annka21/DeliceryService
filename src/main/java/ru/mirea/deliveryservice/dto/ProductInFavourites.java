package ru.mirea.deliveryservice.dto;

import lombok.Data;
import ru.mirea.deliveryservice.models.Product;

@Data
public class ProductInFavourites {

    private Long id;
    private Product product;
}
