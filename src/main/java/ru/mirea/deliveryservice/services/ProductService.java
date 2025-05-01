package ru.mirea.deliveryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mirea.deliveryservice.dto.CreateProductRequest;
import ru.mirea.deliveryservice.models.Product;
import ru.mirea.deliveryservice.repos.ProductRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public void create(CreateProductRequest request) {
        Product product = Product.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .discount(request.getDiscount())
                .availableNumber(request.getAvailableAmount())
                .build();

        productRepo.save(product);
    }

    public Product findOne(Long id) {
        return productRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

//    public void increaseAmount(IncreaseProductAmountRequest request) {
//
//    }
}
