package com.tasto.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String fullName;
    @Column
    private String addressType;
    @Column
    private boolean isDefault;
    @Column
    private String houseNo;
    @Column
    private String street;
    @Column
    private String landmark;
    @Column
    private String city;
    @Column
    private String pinCode;
    @Column
    private String state;
    @Column
    private String country;
    @Column
    private BigDecimal longitude;
    @Column
    private BigDecimal latitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private UserModel user;
}
