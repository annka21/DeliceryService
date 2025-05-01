package ru.mirea.deliveryservice.dto;

import lombok.Data;
import ru.mirea.deliveryservice.models.Order;

import java.util.List;

@Data
public class OrdersResponse {

    private Order order;
    //private List<ProductInOrderItem> orderProducts;
}
