package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
