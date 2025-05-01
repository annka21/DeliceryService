package ru.mirea.deliveryservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "client_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_address")
    private String address;
}
