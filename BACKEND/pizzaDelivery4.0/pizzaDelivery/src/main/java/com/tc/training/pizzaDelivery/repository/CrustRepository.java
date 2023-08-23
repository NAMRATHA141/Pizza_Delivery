package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Crust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CrustRepository extends JpaRepository<Crust, Long> {

    @Query("SELECT c.price FROM CRUST c WHERE c.id = :id")
    BigDecimal findPriceById(@Param("id") Long id);
}
