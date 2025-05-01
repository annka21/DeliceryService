package ru.mirea.deliveryservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.deliveryservice.models.FavouritesItem;

import java.util.List;

@Repository
public interface FavouritesRepo extends JpaRepository<FavouritesItem, Long> {
    List<FavouritesItem> findAllByUserId(Long id);
}
