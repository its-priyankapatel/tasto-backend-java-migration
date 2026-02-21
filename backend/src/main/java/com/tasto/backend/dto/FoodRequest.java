package com.tasto.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FoodRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private boolean inStock;
    private boolean isVeg;
    private BigDecimal rating;
    private List<String> tags;
    private String image;
    private String category;
    public void setIsVeg(boolean isVeg)
    {
        this.isVeg=isVeg;
    }
    public boolean getIsVeg()
    {
        return this.isVeg;
    }
}
