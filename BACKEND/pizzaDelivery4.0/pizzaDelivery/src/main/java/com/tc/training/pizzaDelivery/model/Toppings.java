package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity(name="TOPPINGS")
@Data
@DynamicInsert
@DynamicUpdate
public class Toppings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private String toppings_id;

    private String name;
    private BigDecimal price;

}

