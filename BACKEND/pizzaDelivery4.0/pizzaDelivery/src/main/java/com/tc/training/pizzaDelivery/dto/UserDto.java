package com.tc.training.pizzaDelivery.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}

