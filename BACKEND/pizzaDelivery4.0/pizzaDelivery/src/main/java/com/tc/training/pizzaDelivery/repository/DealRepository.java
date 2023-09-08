package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByEligibleItem_Id(Long productId);
    Deal getDealById(Long id); // Rename the method to avoid conflicts
}
