package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "OUTLET")
@Data
@DynamicInsert
@DynamicUpdate
public class Outlet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String location;
}
