package com.tc.training.pizzaDelivery.model;

import com.tc.training.pizzaDelivery.enums.DealType;
import com.tc.training.pizzaDelivery.enums.OrderStatus;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "ORDER_TABLE")
@Data
@DynamicInsert
@DynamicUpdate
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "checkout_id")
    private Checkout checkout;

//    @Id
//    private String id = UUID.randomUUID().toString();
//
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "timestamp")
    LocalDateTime timestamp;
//
    private String location;
    private String outletLocation;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
