package com.tasto.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="full_name")
    private String fullName;
    @Column(name="address_type")
    private String addressType;
    @Column(name="default_address")
    private boolean defaultAddress;
    @Column(name="house_no")
    private String houseNo;
    @Column
    private String street;
    @Column
    private String landmark;
    @Column
    private String city;
    @Column(name="pin_code")
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
    @JsonIgnore
    private UserModel user;
}
