package ru.mirea.deliveryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.deliveryservice.dto.ProductInFavourites;
import ru.mirea.deliveryservice.models.FavouritesItem;
import ru.mirea.deliveryservice.models.Product;
import ru.mirea.deliveryservice.models.User;
import ru.mirea.deliveryservice.repos.FavouritesRepo;
import ru.mirea.deliveryservice.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouritesService {

    private final FavouritesRepo favouritesRepo;
    private final UserService userService;
    private final ProductRepo productRepo;

    public List<ProductInFavourites> getAll() {
        Long userId = userService.getCurrentUserId();
        List<FavouritesItem> favouritesItems = favouritesRepo.findAllByUserId(userId);
        List<ProductInFavourites> favouritesList = new ArrayList<>();

        for (FavouritesItem item : favouritesItems) {
            Product product = productRepo.findById(item.getProductId()).get();
            ProductInFavourites favouritesItem = new ProductInFavourites();

            favouritesItem.setId(item.getFavouritesItemId());
            favouritesItem.setProduct(product);
            favouritesList.add(favouritesItem);
        }
        return favouritesList;
    }

    public void addProductToFavourites(Long id) {
        Long userId = userService.getCurrentUserId();;
        FavouritesItem item = new FavouritesItem();
        item.setUserId(userId);
        item.setProductId(id);
        favouritesRepo.save(item);
    }
}
