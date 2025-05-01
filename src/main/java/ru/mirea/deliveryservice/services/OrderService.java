package ru.mirea.deliveryservice.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.deliveryservice.dto.OrdersResponse;
import ru.mirea.deliveryservice.dto.ProductInOrderItem;
import ru.mirea.deliveryservice.models.CartItem;
import ru.mirea.deliveryservice.models.Order;
import ru.mirea.deliveryservice.models.OrderItem;
import ru.mirea.deliveryservice.models.Product;
import ru.mirea.deliveryservice.repos.CartItemRepo;
import ru.mirea.deliveryservice.repos.OrderItemRepo;
import ru.mirea.deliveryservice.repos.OrderRepo;
import ru.mirea.deliveryservice.repos.ProductRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final ProductRepo productRepo;
    private final CartItemRepo cartItemRepo;
    private final UserService userService;

    @Transactional
    public List<Order> getOrders() {
        Long userId = userService.getCurrentUserId();

        List<OrdersResponse> ordersResponses = new ArrayList<>();
        return orderRepo.findAllByUserId(userId);
//
//         for (Order order : orders) {
//             //List<OrderItem> orderItems = orderItemRepo.findAllByOrderId(order.getId());
//             //List<ProductInOrderItem> productInOrderItems = new ArrayList<>();
//
////             for (OrderItem item : orderItems) {
////                 Product product = productRepo.findById(item.getProductId()).get();
////                 ProductInOrderItem productInOrderItem = new ProductInOrderItem();
////
////                 productInOrderItem.setProduct(product);
////                 productInOrderItem.setAmount(item.getAmount());
////
////                 productInOrderItems.add(productInOrderItem);
////             }
//
//             OrdersResponse response = new OrdersResponse();
//             response.setOrder(order);
//             //response.setOrderProducts(productInOrderItems);
//
//             ordersResponses.add(response);
//         }
//         return ordersResponses;
    }

    @Transactional
    public void createOrder() {
        // достаем все cartItems
        // создаем order
        // создаем orderItems
        // удаляем cartItems
        Long userId = userService.getCurrentUserId();

        List<CartItem> cartItems = cartItemRepo.findAllByUserId(userId);
        List<Product> productsInCart = new ArrayList<>();
        for (CartItem item : cartItems) {
            productsInCart.add(productRepo.findById(item.getProductId()).get());
        }
        Double totalPrice = productsInCart.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        Order order = new Order();
        order.setCreationTime(new Date());
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setAddress("");
        orderRepo.save(order);

        for (CartItem item : cartItems) {
            if (!item.getChecked()) continue;
            OrderItem orderItem = new OrderItem();
            orderItem.setAmount(item.getAmount());
            orderItem.setProductId(item.getProductId());
            orderItem.setOrderId(order.getId());
            orderItemRepo.save(orderItem);
        }
        cartItemRepo.deleteAllInBatch(cartItems);
    }
}
