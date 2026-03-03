package com.tasto.backend.dto;

import com.tasto.backend.entity.FoodModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class FoodResponse {
    private boolean success;
    private String message;
    private List<FoodModel> food;
}
