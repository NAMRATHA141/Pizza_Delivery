package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.Checkout;

public interface CheckoutService {
    Checkout saveCheckout(Checkout checkout);
    void deleteCheckout(Long checkoutId);


}
