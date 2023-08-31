package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Deal;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DealService {
    List<Deal> getAllDeals();
    Optional<Deal> getDealById(Long id);
    Deal createDeal(Deal deal);
    void deleteDeal(Long id);
//    BigDecimal calculateDiscountedPrice(CartItem cartItem);
}

