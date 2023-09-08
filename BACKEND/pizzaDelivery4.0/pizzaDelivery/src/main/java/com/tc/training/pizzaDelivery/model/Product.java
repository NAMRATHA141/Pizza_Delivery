package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;


@Entity(name = "PRODUCT")
@Data
@DynamicInsert
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String type;
    private String category;
    private String spicy;
    private boolean isVeg;
    private boolean isAlcoholic;
    private String image;
    @ManyToMany
    @JoinTable(
            name = "product_toppings",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "toppings_id")
    )
    private List<Toppings> toppings;



}
