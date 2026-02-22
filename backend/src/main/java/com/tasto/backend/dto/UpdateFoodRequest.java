package com.tasto.backend.dto;

import com.tasto.backend.entity.CategoryModel;
import com.tasto.backend.entity.FoodModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateFoodRequest {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean inStock;
    private BigDecimal rating;
    private boolean isVeg;
    private List<String> tags;
    private String image;
    private CategoryModel category;

    public void setInStock(boolean inStock)
    {
        this.inStock = inStock;
    }
    public boolean getIsStock()
    {
        return this.inStock;
    }

}
