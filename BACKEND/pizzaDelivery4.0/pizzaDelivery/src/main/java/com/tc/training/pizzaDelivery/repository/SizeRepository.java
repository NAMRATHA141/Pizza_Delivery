package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query("SELECT s.price_adjustment FROM SIZE s WHERE s.id = :id")
    BigDecimal findPriceById(@Param("id") Long id);
}
