package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Toppings;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }

    public CartItem saveCartItem(CartItem cartItem) {
        BigDecimal calculatedPrice = calculateCartItemPrice(cartItem);
        cartItem.setItem_price(calculatedPrice);

        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public List<CartItem> getCartItemsByCustomerId(Long user_id) {
        return cartItemRepository.findByUserId(user_id);
    }

    private BigDecimal calculateCartItemPrice(CartItem cartItem) {
        BigDecimal basePrice = cartItem.getProduct().getPrice();
        BigDecimal crustPrice = cartItem.getCrust().getPrice();
        BigDecimal sizePrice = cartItem.getSize().getPrice_adjustment();
        BigDecimal toppingsPrice = calculateToppingsPrice(cartItem.getToppings());
        int quantity = cartItem.getQuantity();

        BigDecimal totalPrice = basePrice
                .add(crustPrice)
                .multiply(sizePrice)
                .add(toppingsPrice)
                .multiply(BigDecimal.valueOf(quantity));

        return totalPrice;
    }

    private BigDecimal calculateToppingsPrice(List<Toppings> toppings) {

        BigDecimal toppingsPrice = BigDecimal.ZERO;
        for (Toppings topping : toppings) {
            toppingsPrice = toppingsPrice.add(topping.getPrice());
        }
        return toppingsPrice;
    }
}


