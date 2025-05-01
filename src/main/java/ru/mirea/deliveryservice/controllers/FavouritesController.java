package ru.mirea.deliveryservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.deliveryservice.dto.ProductIdRequest;
import ru.mirea.deliveryservice.dto.ProductInFavourites;
import ru.mirea.deliveryservice.services.FavouritesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourites")
@RequiredArgsConstructor
public class FavouritesController {

    private final FavouritesService favouritesService;

    @GetMapping
    public ResponseEntity<List<ProductInFavourites>> getFavourites() {
        return ResponseEntity.ok(favouritesService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addProductToFavourites(@RequestBody ProductIdRequest request) {
        favouritesService.addProductToFavourites(request.getId());
        return ResponseEntity.ok().build();
    }
}
