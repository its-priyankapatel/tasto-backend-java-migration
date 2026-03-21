package com.tasto.backend.service;


import com.tasto.backend.dto.*;

public interface RestaurantService {
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);
    RestaurantLoginResponse restaurantLogin(RestaurantLoginRequest restaurantLoginRequest);
    RestaurantResponse getRestaurantById(Long id);
    RestaurantResponse deleteRestaurantAccount();
    RestaurantResponse updateRestaurant(RequestUpdateRestaurant requestUpdateRestaurant);
}
