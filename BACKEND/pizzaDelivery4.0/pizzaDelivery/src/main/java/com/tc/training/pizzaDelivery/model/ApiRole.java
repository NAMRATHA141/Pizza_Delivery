package com.tc.training.pizzaDelivery.model;

import com.tc.training.pizzaDelivery.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;


@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class ApiRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Role role;

    @ManyToMany
    @JoinTable(
            name = "api_role_mapping",
            joinColumns = @JoinColumn(name = "api_role_id"),
            inverseJoinColumns = @JoinColumn(name = "api_id")
    )
    private List<ApiEntity> apis;


}
