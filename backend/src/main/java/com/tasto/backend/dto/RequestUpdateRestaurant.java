package com.tasto.backend.dto;

import com.tasto.backend.entity.FoodModel;
import com.tasto.backend.entity.RestaurantAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateRestaurant {
    private String name;
    private String description;
    private String mobile;
    private String openingTime;
    private String closingTime;
    private List<String> tags;
    private boolean restaurantOpen;
    private boolean acceptOrder;
    private BigDecimal startingPrice=new BigDecimal(0);
    private RestaurantAddress addresses;
}
