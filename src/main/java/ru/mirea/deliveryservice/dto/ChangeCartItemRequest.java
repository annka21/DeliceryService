package ru.mirea.deliveryservice.dto;

import lombok.Data;

@Data
public class ChangeCartItemRequest {

    private Integer amount;
    private Boolean checked;
}
