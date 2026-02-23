package com.tasto.backend.service;

import com.tasto.backend.dto.FoodRequest;
import com.tasto.backend.dto.FoodResponse;
import com.tasto.backend.dto.UpdateFoodRequest;
import com.tasto.backend.entity.CategoryModel;
import com.tasto.backend.entity.FoodModel;
import com.tasto.backend.exception.InvalidRequestException;
import com.tasto.backend.repository.CategoryRepository;
import com.tasto.backend.repository.FoodRepository;
import com.tasto.backend.utils.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        category.get().setCount(category.get().getCount()+1);
        categoryRepository.save(category.get());
        food.setCategory(category.get());
      FoodModel newFood = foodRepository.save(food);
      return new FoodResponse(true, "food created successfully",List.of(newFood));
    }

    @Override
    public FoodResponse getFoodByCategory(Long categoryId)
    {
        if(categoryId==null)
        {
            throw new InvalidRequestException("Must provided category id");
        }
       Optional <List<FoodModel>> food = foodRepository.findByCategory_Id(categoryId);
        if(!food.isPresent())
        {
            return new FoodResponse(true,"Food not found",new ArrayList<>());
        }
        return new FoodResponse(true,"Food get successfully",food.get());
    }
    @Override
    public FoodResponse getFoodById(Long id)
    {
        if(id==null)
        {
            throw new InvalidRequestException("Provide valid Id");
        }
       Optional<FoodModel> food =foodRepository.findById(id);
        if(!food.isPresent())
        {
            return new FoodResponse(true,"Food not found",null);
        }
        return new FoodResponse(true,"Food found successfully",List.of(food.get()));
    }
    @Override
    public FoodResponse updateFood(UpdateFoodRequest updateFoodRequest)
    {
        if(updateFoodRequest==null)
        {
          throw new InvalidRequestException("Please provide the field to update");
        }
        if(updateFoodRequest.getId()==null)
        {
            throw new InvalidRequestException("Please provide the Id");
        }
        Optional<FoodModel> food = foodRepository.findById(updateFoodRequest.getId());
        if(!food.isPresent())
        {
            return new FoodResponse(true,"Food not found",null);
        }
        FoodModel newFood=new FoodModel();
        newFood.setId(updateFoodRequest.getId());
        newFood.setName(updateFoodRequest.getName());
        newFood.setDescription(updateFoodRequest.getDescription());
        newFood.setImage(updateFoodRequest.getImage());
        newFood.setCategory(updateFoodRequest.getCategory());
        newFood.setTags(updateFoodRequest.getTags());
        newFood.setIsVeg(updateFoodRequest.isVeg());
        newFood.setInStock(updateFoodRequest.getIsStock());
        newFood.setRating(updateFoodRequest.getRating());
        newFood.setPrice(updateFoodRequest.getPrice());

        FoodModel updatedFood = foodRepository.save(newFood);
        return new FoodResponse(true,"Food updated successfully",List.of(updatedFood));
    }
    @Override
    public FoodResponse deleteFood(Long foodId) {
        if (foodId == null) {
            throw new InvalidRequestException("Please provide food Id");
        }

        Optional<FoodModel> food=foodRepository.findById(foodId);
        if(!food.isPresent())
        {
            return new FoodResponse(true,"Food not found",null);
        }
       CategoryModel cat=food.get().getCategory();
        cat.setCount(cat.getCount()-1);
        categoryRepository.save(cat);
        foodRepository.deleteById(foodId);
        return new FoodResponse(true,"Food deleted successfully",null);
    }
}
