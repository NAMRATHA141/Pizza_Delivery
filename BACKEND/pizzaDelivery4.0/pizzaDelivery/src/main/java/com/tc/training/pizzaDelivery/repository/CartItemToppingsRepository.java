package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemToppingsRepository extends JpaRepository<CartItem, Long> {
}
