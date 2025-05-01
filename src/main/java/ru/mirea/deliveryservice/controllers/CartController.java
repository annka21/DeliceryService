package ru.mirea.deliveryservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.deliveryservice.dto.ChangeCartItemRequest;
import ru.mirea.deliveryservice.dto.ProductIdRequest;
import ru.mirea.deliveryservice.dto.ProductInCartItem;
import ru.mirea.deliveryservice.services.CartService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<ProductInCartItem>> getCart() {
        return ResponseEntity.ok(cartService.getAllInCart());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> updateCartItem(@PathVariable(name = "id") Long id, @RequestBody ChangeCartItemRequest request) {
        cartService.updateCartItem(id, request.getAmount(), request.getChecked());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addToCart(@RequestBody ProductIdRequest request) {
        cartService.addToCart(request.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFromCart(@PathVariable(name = "id") Long id) {
        cartService.deleteFromCart(id);
        return ResponseEntity.ok().build();
    }
}
