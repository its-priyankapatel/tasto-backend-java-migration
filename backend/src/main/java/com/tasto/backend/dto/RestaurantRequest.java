package com.tasto.backend.dto;

import com.tasto.backend.entity.Address;
import com.tasto.backend.entity.RestaurantAddress;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RestaurantRequest {
    private String name;
    private String description;
    private String mobile;
    private String email;
    private String password;
    private String openingTime;
    private String closingTime;
    private BigDecimal rating=new BigDecimal(0);
    private List<String>tags;
    private boolean restaurantOpen;
    private  boolean acceptOrder;
    private RestaurantAddress address;
    private BigDecimal startingPrice=new BigDecimal(0);
}
