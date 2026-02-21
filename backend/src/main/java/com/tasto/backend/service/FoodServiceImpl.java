package com.tasto.backend.service;

import com.tasto.backend.dto.FoodRequest;
import com.tasto.backend.dto.FoodResponse;
import com.tasto.backend.entity.CategoryModel;
import com.tasto.backend.entity.FoodModel;
import com.tasto.backend.exception.InvalidRequestException;
import com.tasto.backend.repository.CategoryRepository;
import com.tasto.backend.repository.FoodRepository;
import com.tasto.backend.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;
    public FoodServiceImpl(FoodRepository foodRepository,CategoryRepository categoryRepository)
    {
        this.foodRepository=foodRepository;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public FoodResponse addFood(FoodRequest foodRequest)
    {
        Validator.validateFoodRequest(foodRequest);

        FoodModel food=new FoodModel();
        food.setName(foodRequest.getName());
        food.setDescription(foodRequest.getDescription());
        food.setPrice(foodRequest.getPrice());
        food.setRating(foodRequest.getRating());
        food.setInStock(foodRequest.isInStock());
        food.setIsVeg(foodRequest.getIsVeg());
        food.setTags(foodRequest.getTags());
        food.setImage(foodRequest.getImage());

        Optional<CategoryModel> category = categoryRepository.findByName(foodRequest.getCategory().toLowerCase());
        if(!category.isPresent())
        {
            throw new InvalidRequestException("Please pass correct category");
        }
        food.setCategory(category.get());
      FoodModel newFood = foodRepository.save(food);
      return new FoodResponse(true, "food created successfully", newFood);
    }
}
