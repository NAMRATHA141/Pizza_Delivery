package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Toppings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ToppingsRepository extends JpaRepository<Toppings, Long> {
    @Query("SELECT t.price FROM TOPPINGS t WHERE t.id = :id")
    BigDecimal findPriceById(@Param("id") Long id);
}
