package ru.mirea.deliveryservice.dto;

import lombok.Data;
import ru.mirea.deliveryservice.models.Product;

@Data
public class ProductInCartItem {

    private Long id;
    private Product product;
    private Integer amount;
    private Boolean checked;
}
