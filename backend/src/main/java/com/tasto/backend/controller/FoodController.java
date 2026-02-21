package com.tasto.backend.controller;

import com.tasto.backend.dto.FoodRequest;
import com.tasto.backend.dto.FoodResponse;
import com.tasto.backend.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;
    public FoodController(FoodService foodService)
    {
        this.foodService=foodService;
    }

    @PostMapping("/add-food")
    public ResponseEntity<FoodResponse> createFood(@RequestBody FoodRequest foodRequest)
    {
       FoodResponse response = foodService.addFood(foodRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
