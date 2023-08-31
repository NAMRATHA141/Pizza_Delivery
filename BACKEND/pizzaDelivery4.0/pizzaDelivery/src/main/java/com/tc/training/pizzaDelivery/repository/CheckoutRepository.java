package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

}
