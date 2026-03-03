package com.tasto.backend.repository;


import com.tasto.backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findByEmail(String email);
    Optional<Restaurant> findById(Long id);
}
