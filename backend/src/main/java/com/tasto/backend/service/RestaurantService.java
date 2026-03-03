package com.tasto.backend.service;


import com.tasto.backend.dto.RestaurantLoginRequest;
import com.tasto.backend.dto.RestaurantLoginResponse;
import com.tasto.backend.dto.RestaurantRequest;
import com.tasto.backend.dto.RestaurantResponse;

public interface RestaurantService {
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);
    RestaurantLoginResponse restaurantLogin(RestaurantLoginRequest restaurantLoginRequest);
    RestaurantResponse getRestaurantById(Long id);
    RestaurantResponse deleteRestaurantAccount();
}
