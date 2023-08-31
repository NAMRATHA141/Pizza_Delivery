package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "CART_ITEM")
@Data
@DynamicUpdate
@DynamicInsert
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "crust_id")
    private Crust crust;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @Nullable
    @ManyToMany
    @JoinTable(
            name = "cart_item_toppings",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "toppings_id")
    )
    private List<Toppings> toppings;
    private BigDecimal item_price;
//    private BigDecimal item_discount_price;

    private int quantity;

    public void setItem_price(BigDecimal item_price) {
        this.item_price = item_price;
    }
//    public void setItem_discount_price(BigDecimal item_discount_price){this.item_discount_price = item_discount_price; }


}
