package com.tasto.backend.dto;

import com.tasto.backend.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {
    private boolean success;
    private String message;
    private Restaurant restaurant;
}
