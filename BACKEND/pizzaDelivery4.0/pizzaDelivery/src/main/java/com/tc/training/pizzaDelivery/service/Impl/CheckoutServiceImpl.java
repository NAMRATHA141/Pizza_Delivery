package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Checkout;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.repository.CheckoutRepository;
import com.tc.training.pizzaDelivery.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CartItemRepository cartItemRepository;
    public Checkout saveCheckout(Checkout checkout) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(checkout.getUser().getId());
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart items are empty or null. Cannot create an order.");
        }
        checkout.setCartItems(cartItems);
        BigDecimal subtotal = calculateSubtotal(cartItems);
        checkout.setSubtotal(subtotal);

        return checkoutRepository.save(checkout);
    }
    public void deleteCheckout(Long checkoutId) {
        checkoutRepository.deleteById(checkoutId);
    }

    private BigDecimal calculateSubtotal(List<CartItem> cartItems) {
        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            subtotal = subtotal.add(cartItem.getItem_price());
        }

        return subtotal;
    }

    //    public BigDecimal calculateDiscountedPrice(CartItem cartItem) {
//        Long id = cartItem.getId();
//        BigDecimal itemPrice =  cartItemService.calculateCartItemPrice(cartItem);
//        BigDecimal discount = calculateDiscount(cartItem, itemPrice);
//        BigDecimal discountedPrice = itemPrice.subtract(discount);
//        return discountedPrice;
//    }
//

//    private void calculateAndSetDiscountedPrices(List<CartItem> cartItems) {
//        for (CartItem cartItem : cartItems) {
//            BigDecimal discountedPrice = dealService.calculateDiscountedPrice(cartItem);
//            cartItem.setItem_discount_price(discountedPrice); // Set discounted price in CartItem
//        }
//    }
}
