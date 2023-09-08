package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutletRepository extends JpaRepository<Outlet, Long> {
    Outlet findByUserId(Long userId);
}