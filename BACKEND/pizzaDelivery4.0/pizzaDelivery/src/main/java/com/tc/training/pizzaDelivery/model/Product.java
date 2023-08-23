package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;


@Entity(name = "PRODUCT")
@Data
@DynamicInsert
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String product_id;

    private String name;
    private String description;
    private BigDecimal price;
    private String type;
    private String category;
    private String spicy;
    private boolean isVeg;
    private boolean isAlcoholic;
    private String image;


}
