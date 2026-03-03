package com.tasto.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="house_no")
    private String houseNo;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String country;
    @Column
    private BigDecimal latitude;
    @Column
    private BigDecimal longitude;
    @OneToOne
    @JoinColumn(name = "restaurant_id",nullable = false,unique = true)
    private Restaurant restaurant;
}
