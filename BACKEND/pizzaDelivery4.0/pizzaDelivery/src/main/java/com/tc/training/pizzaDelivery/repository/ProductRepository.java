package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p.price FROM PRODUCT p WHERE p.id = :id")
    BigDecimal findPriceById(@Param("id") Long id);
}
