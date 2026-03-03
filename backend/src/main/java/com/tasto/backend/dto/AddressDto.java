package com.tasto.backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddressDto {
    private Long id;
    private String fullName;
    private String addressType;
    private boolean defaultAddress;
    private String houseNo;
    private String street;
    private String landmark;
    private String city;
    private String pinCode;
    private String state;
    private String country;
    private BigDecimal longitude;
    private BigDecimal latitude;

}
