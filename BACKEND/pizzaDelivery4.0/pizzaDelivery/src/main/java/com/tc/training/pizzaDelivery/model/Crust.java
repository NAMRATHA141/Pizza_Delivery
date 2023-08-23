package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity(name="Crust")
@Data
@DynamicInsert
@DynamicUpdate
public class Crust {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String crust_id;


    private String name;
    private BigDecimal price;

}

