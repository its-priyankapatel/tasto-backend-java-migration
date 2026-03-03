package com.tasto.backend.dto;

import lombok.Data;

@Data
public class RestaurantLoginRequest {
    private String email;
    private String password;
}
