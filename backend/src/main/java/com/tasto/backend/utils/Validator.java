package com.tasto.backend.utils;

import com.tasto.backend.dto.FoodRequest;
import com.tasto.backend.dto.UserRequest;
import com.tasto.backend.exception.InvalidRequestException;

import java.math.BigDecimal;

public class Validator {
     public static void validateFoodRequest(FoodRequest foodRequest)
    {
        if(foodRequest.getName()==null || foodRequest.getName().isBlank())
        {
            throw new InvalidRequestException("Food name must be provided");
        }
        if(foodRequest.getPrice().compareTo(BigDecimal.ZERO)<0)
        {
            throw new InvalidRequestException("Price must be greater than 0");
        }
        BigDecimal rating = foodRequest.getRating();

        if (rating.compareTo(BigDecimal.ZERO) < 0 ||
                rating.compareTo(BigDecimal.valueOf(5)) > 0) {
            throw new InvalidRequestException("Rating should be in the range of 0 to 5");
        }

        if(foodRequest.getCategory()==null || foodRequest.getCategory().isBlank())
        {
            throw new InvalidRequestException("Category must be provided");
        }

    }
    public static void validateUserRequest(UserRequest userRequest)
    {
        if(userRequest.getName()==null || userRequest.getName().isBlank())
        {
            throw new InvalidRequestException("Name must be provided");
        }
        if(userRequest.getEmail()==null || userRequest.getEmail().isBlank())
        {
            throw new InvalidRequestException("Email must be provided");
        }
        if(userRequest.getPhone()==null || userRequest.getPhone().isBlank())
        {
            throw new InvalidRequestException("Phone number must be provided");
        }
        if(userRequest.getPassword()==null || userRequest.getPassword().isBlank())
        {
            throw new InvalidRequestException("Password must be provided");
        }
        if(userRequest.getName().length()<3)
        {
            throw new InvalidRequestException("Name must be least 3 characters");
        }
        if(!userRequest.getEmail().contains("@"))
        {
            throw new InvalidRequestException("Please provide valid email");
        }
        if(userRequest.getPhone().length()!=10)
        {
            throw new InvalidRequestException("Phone number must be 10 digits");
        }
        String phone=userRequest.getPhone();
        if(phone.matches("^[0-9]+$"))
        {
            throw new InvalidRequestException("Phone number must be digits only");
        }
    }
}
