package ru.mirea.deliveryservice.dto;

import lombok.Data;
import ru.mirea.deliveryservice.models.Product;

@Data
public class ProductInOrderItem {

    private Product product;
    private Integer amount;
}
