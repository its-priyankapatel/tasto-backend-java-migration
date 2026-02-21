package com.tasto.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name="foods")
@NoArgsConstructor
@AllArgsConstructor
public class FoodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false,length = 50)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column
    private boolean inStock;
    @Column
    private BigDecimal rating;
    @Column(name = "isVeg")
    private boolean isVeg;
    @Column
    private List<String> tags;
    @Column
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    public void setIsVeg(boolean isVeg)
    {
        this.isVeg=isVeg;
    }
    public boolean getIsVeg()
    {
        return this.isVeg;
    }

}
