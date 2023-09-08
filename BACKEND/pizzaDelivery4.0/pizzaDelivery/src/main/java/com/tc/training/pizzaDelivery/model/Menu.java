package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity(name = "MENU")
@Data
@DynamicInsert
@DynamicUpdate
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "menu_product",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @ManyToMany
    @JoinTable(
            name = "menu_crust",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "crust_id")
    )
    private List<Crust> crust;

    @ManyToMany
    @JoinTable(
            name = "menu_size",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private List<Size> size;

    @ManyToMany
    @JoinTable(
            name = "menu_toppings",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "toppings_id")
    )
    private List<Toppings> toppings;

    private String outletAddress;
}
