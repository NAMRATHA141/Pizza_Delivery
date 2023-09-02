package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "CHECKOUT")
@Data
@DynamicUpdate
@DynamicInsert
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "checkout_cart",
            joinColumns = @JoinColumn(name = "checkout_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item_id")
    )
    private List<CartItem> cartItems;

    private BigDecimal grandTotal;
    private BigDecimal discountPrice;
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "deal_id")
    private Deal deal;

}
