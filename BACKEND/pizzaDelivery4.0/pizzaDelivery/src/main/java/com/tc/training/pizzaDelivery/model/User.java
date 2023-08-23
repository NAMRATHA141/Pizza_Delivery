package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="USER")
@Data
@DynamicInsert
@DynamicUpdate
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user_id;

    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String department;
    private String role;
    private String phoneNumber;


}
