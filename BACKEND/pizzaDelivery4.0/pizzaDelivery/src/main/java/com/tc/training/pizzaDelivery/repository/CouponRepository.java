package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
