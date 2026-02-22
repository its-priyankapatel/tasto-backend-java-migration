package com.tasto.backend.controller;

import com.tasto.backend.dto.FoodRequest;
import com.tasto.backend.dto.FoodResponse;
import com.tasto.backend.dto.UpdateFoodRequest;
import com.tasto.backend.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-food")
    public ResponseEntity<FoodResponse> getFood(@RequestParam(required = false) Long category,@RequestParam(required = false)Long food)
    {
        FoodResponse response=null;
        if(category!=null) {
            response = foodService.getFoodByCategory(category);
        }
        else if(food!=null)
        {
            response = foodService.getFoodById(food);
        }
        else {
            response = new FoodResponse(false,"Please provide correct parameter",null);
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/update-food")
    public ResponseEntity<FoodResponse>foodUpdate(@RequestBody UpdateFoodRequest updateFoodRequest)
    {
        FoodResponse response  = foodService.updateFood(updateFoodRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/delete-food")
    public ResponseEntity<FoodResponse> foodDelete(@RequestParam Long foodId)
    {
          FoodResponse response = foodService.deleteFood(foodId);
          return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
