package ru.mirea.deliveryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.deliveryservice.dto.ProductInCartItem;
import ru.mirea.deliveryservice.models.CartItem;
import ru.mirea.deliveryservice.models.Product;
import ru.mirea.deliveryservice.repos.CartItemRepo;
import ru.mirea.deliveryservice.repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepo cartItemRepo;
    private final ProductRepo productRepo;
    private final UserService userService;

    public List<ProductInCartItem> getAllInCart() {
        Long userId = userService.getCurrentUserId();
        List<ProductInCartItem> productInCartItemList = new ArrayList<>();
        List<CartItem> cartItems = cartItemRepo.findAllByUserId(userId);
        for (CartItem item : cartItems) {
            ProductInCartItem productInCartItem = new ProductInCartItem();
            Product product = productRepo.findById(item.getProductId()).get();

            productInCartItem.setId(item.getId());
            productInCartItem.setProduct(product);
            productInCartItem.setAmount(item.getAmount());
            productInCartItem.setChecked(item.getChecked());

            productInCartItemList.add(productInCartItem);
        }
        return productInCartItemList;
    }

    public void addToCart(Long productId) {
        Long userId = userService.getCurrentUserId();
        CartItem item = new CartItem();
        item.setUserId(userId);
        item.setProductId(productId);
        item.setAmount(1);
        item.setChecked(false);

        cartItemRepo.save(item);
    }
    
    public void updateCartItem(Long itemId, Integer newAmount, Boolean checked) {
        CartItem item = cartItemRepo.findById(itemId).get();
        if (newAmount != null) {
            item.setAmount(newAmount);
        }
        if (checked != null) {
            item.setChecked(checked);
        }
        cartItemRepo.save(item);
    }

    public void deleteFromCart(Long itemId) {
        cartItemRepo.deleteById(itemId);
    }
}
