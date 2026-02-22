package com.tasto.backend.repository;

import com.tasto.backend.entity.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodModel,Long> {
    Optional<FoodModel>findById(Long id);
    Optional<List<FoodModel>>findByCategory_Id(Long categoryId);
}
