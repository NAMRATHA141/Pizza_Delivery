package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Toppings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingsRepository extends JpaRepository<Toppings, Long> {
}
