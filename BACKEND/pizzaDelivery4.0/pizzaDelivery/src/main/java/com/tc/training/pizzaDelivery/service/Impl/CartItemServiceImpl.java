package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Toppings;
import com.tc.training.pizzaDelivery.repository.*;
import com.tc.training.pizzaDelivery.service.CartItemService;
//import com.tc.training.pizzaDelivery.service.DealService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CrustRepository crustRepository;
    private final SizeRepository sizeRepository;
    private final ToppingsRepository toppingsRepository;
    private final CartItemToppingsRepository cartItemToppingsRepository;
//    private final DealService dealService;

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
        cartItemToppingsRepository.deleteById(cartItemId);
        cartItemRepository.deleteById(cartItemId);

    }

    public List<CartItem> getCartItemsByCustomerId(Long user_id) {
        return cartItemRepository.findByUser_Id(user_id);
    }

    public BigDecimal calculateCartItemPrice(CartItem cartItem) {
        if (cartItem.getProduct() == null) {
            throw new IllegalStateException("No product is added to the cart"); // Client side error 400 series
        }
        BigDecimal basePrice = productRepository.findPriceById(cartItem.getProduct().getId());
        BigDecimal crustPrice = BigDecimal.ZERO;
        if (cartItem.getCrust() != null) {
            crustPrice = crustRepository.findPriceById(cartItem.getCrust().getId());
        }
        BigDecimal sizePrice = sizeRepository.findPriceById(cartItem.getSize().getId());
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
        if (toppings == null) {
            return BigDecimal.ZERO; // No toppings, return zero price
        }

        for (Toppings topping : toppings) {
            toppingsPrice = toppingsPrice.add(toppingsRepository.findPriceById(topping.getId()));

        }
        return toppingsPrice;
    }
}


