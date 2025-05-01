package ru.mirea.deliveryservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "favourites_item")
@Data
@RequiredArgsConstructor
public class FavouritesItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourites_item_id")
    private Long favouritesItemId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;
}
