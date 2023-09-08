package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.Coupon;

import java.util.List;

public interface CouponService {

    List<Coupon> getAllCoupons();

    Coupon getCouponById(Long id);

}
