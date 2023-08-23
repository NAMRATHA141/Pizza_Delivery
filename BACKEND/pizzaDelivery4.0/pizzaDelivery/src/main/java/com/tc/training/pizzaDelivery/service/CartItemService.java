package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    CartItem getCartItemById(Long cartItemId);

    CartItem saveCartItem(CartItem cartItem);

    void deleteCartItem(Long cartItemId);

    List<CartItem> getCartItemsByCustomerId(Long customerId);
}
