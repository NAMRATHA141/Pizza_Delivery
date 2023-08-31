package com.tc.training.pizzaDelivery.model;

import com.tc.training.pizzaDelivery.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "USER")
@Data
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotNull
    private String email;
    private String phoneNumber;
    private String address;
    @NotNull
    private String firebaseId;
    @NotNull
    @Enumerated(EnumType.STRING)
    public Role role;

}
