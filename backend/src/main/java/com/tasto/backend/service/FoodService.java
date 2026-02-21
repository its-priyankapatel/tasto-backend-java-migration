package com.tasto.backend.service;

import com.tasto.backend.dto.FoodRequest;
import com.tasto.backend.dto.FoodResponse;

public interface FoodService {
    FoodResponse addFood(FoodRequest foodRequest);
}
