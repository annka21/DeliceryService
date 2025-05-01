package ru.mirea.deliveryservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.deliveryservice.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
