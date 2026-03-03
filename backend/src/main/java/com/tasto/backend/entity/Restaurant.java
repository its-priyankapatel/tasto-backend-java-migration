package com.tasto.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(unique = true)
    private String mobile;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private BigDecimal rating = new BigDecimal(0);
    @Column
    private String openingTime;
    @Column
    private String closingTime;
    @Column
    private List<String>tags;
    @Column(name="is_restaurant_open")
    private boolean restaurantOpen;
    @Column(name="is_accepting_order")
    private boolean acceptOrder;
    @Column(name = "starting_price")
    private BigDecimal startingPrice=new BigDecimal(0);
    @Column(name="is_account_active")
    private boolean accountActive = true;
    @OneToOne(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private RestaurantAddress addresses;
    @OneToOne(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FoodModel> foods;
    public void setAddresses(RestaurantAddress addresses)
    {
        addresses.setRestaurant(this);
        this.addresses=addresses;
    }
}
