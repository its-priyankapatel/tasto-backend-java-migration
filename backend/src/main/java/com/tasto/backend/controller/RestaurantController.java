package com.tasto.backend.controller;

import com.tasto.backend.dto.*;
import com.tasto.backend.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
    }
    @PostMapping("/register")
    ResponseEntity<RestaurantResponse>registerRestaurant(@RequestBody RestaurantRequest restaurantRequest)
    {
        RestaurantResponse response = restaurantService.addRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    ResponseEntity<RestaurantLoginResponse> loginRestaurant(@RequestBody RestaurantLoginRequest restaurantLoginRequest)
    {
       RestaurantLoginResponse response=restaurantService.restaurantLogin(restaurantLoginRequest);
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/get-restaurant")
    ResponseEntity<RestaurantResponse>restaurantRetrieve(@RequestParam Long restaurantId)
    {
        RestaurantResponse response = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/deactivate")
    ResponseEntity<RestaurantResponse>deleteRestaurant()
    {
        RestaurantResponse response=restaurantService.deleteRestaurantAccount();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PatchMapping("/update-restaurant")
    ResponseEntity<RestaurantResponse>modifyRestaurant(@RequestBody RequestUpdateRestaurant requestUpdateRestaurant)
    {
        RestaurantResponse response=restaurantService.updateRestaurant(requestUpdateRestaurant);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
