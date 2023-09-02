package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Checkout;
import com.tc.training.pizzaDelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findById(long id);
    Checkout findByUserId(Long userId);
}
