package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity(name="SIZE")
@Data
@DynamicInsert
@DynamicUpdate
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String size_id;

    private String name;
    private BigDecimal price_adjustment;

}

