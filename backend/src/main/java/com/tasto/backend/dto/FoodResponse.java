package com.tasto.backend.dto;

import com.tasto.backend.entity.FoodModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class FoodResponse {
    private boolean success;
    private String message;
    private FoodModel food;
}
