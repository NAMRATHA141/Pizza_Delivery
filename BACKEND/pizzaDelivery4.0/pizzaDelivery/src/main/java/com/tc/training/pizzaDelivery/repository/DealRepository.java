package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Deal;
import com.tc.training.pizzaDelivery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByEligibleItem_Id(Long productId);

}
