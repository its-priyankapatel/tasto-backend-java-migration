package com.tasto.backend.service;

import com.tasto.backend.dto.FoodRequest;
import com.tasto.backend.dto.FoodResponse;
import com.tasto.backend.dto.UpdateFoodRequest;

public interface FoodService {
    FoodResponse addFood(FoodRequest foodRequest);
    FoodResponse getFoodByCategory(Long categoryId);
    FoodResponse getFoodById(Long id);
    FoodResponse updateFood(UpdateFoodRequest updateFoodRequest);
    FoodResponse deleteFood(Long foodId);
}
