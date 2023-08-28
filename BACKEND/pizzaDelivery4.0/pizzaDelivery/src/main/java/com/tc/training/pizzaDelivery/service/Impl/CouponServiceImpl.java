package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.Coupon;
import com.tc.training.pizzaDelivery.repository.CouponRepository;
import com.tc.training.pizzaDelivery.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon getCouponById(Long id) {
        return couponRepository.findById(id).orElse(null);
    }

}
