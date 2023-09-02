package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;

import java.math.BigDecimal;
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
////
////    @ManyToOne
////    @JoinColumn(name = "deal_id")
////    private Deal deal;
//
//    @ManyToMany
//    @JoinTable(
//            name = "order_cart",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "cart_item_id")
//    )
//    private List<CartItem> cartItems;
//
////    private BigDecimal grandtotal;
////    private BigDecimal discountPrice;
////    private BigDecimal subtotal;
//
    private String location;
    private String outletLocation;

}
