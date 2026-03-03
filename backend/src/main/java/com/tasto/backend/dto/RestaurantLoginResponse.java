package com.tasto.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantLoginResponse {
    private boolean success;
    private String message;
    private String token;
}
