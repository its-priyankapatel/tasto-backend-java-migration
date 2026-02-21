package com.tasto.backend.repository;

import com.tasto.backend.entity.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel,Long> {
    Optional<CategoryModel> findByName(String name);
}
