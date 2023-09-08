package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Deal;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DealService {
    List<Deal> getAllDeals();
    Deal getDealById(Long id);
    Deal createDeal(Deal deal);
    void deleteDeal(Long id);
    BigDecimal calculateDiscount(CartItem cartItem, Deal selectedDeal, BigDecimal itemPrice);

    List<Deal> getApplicableDealsForCartItem(Long cartItemId);
}

