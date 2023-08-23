package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Crust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrustRepository extends JpaRepository<Crust, Long> {
}
