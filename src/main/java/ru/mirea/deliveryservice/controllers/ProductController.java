package ru.mirea.deliveryservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.deliveryservice.dto.CreateProductRequest;
import ru.mirea.deliveryservice.models.Product;
import ru.mirea.deliveryservice.services.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable(name = "id") Long id) {
        try {
            Product product = productService.findOne(id);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> addProduct(@RequestBody CreateProductRequest request) {
        productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
