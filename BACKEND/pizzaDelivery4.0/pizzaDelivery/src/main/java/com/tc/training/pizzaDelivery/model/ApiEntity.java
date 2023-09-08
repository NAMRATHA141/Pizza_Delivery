package com.tc.training.pizzaDelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

@Entity(name = "API_ENTITY")
@Data
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
public class ApiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private RequestMethod method;
}
