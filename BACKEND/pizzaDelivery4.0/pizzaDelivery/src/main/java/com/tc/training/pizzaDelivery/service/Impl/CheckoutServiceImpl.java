package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Checkout;
import com.tc.training.pizzaDelivery.model.Deal;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.repository.CheckoutRepository;
import com.tc.training.pizzaDelivery.service.CheckoutService;
import com.tc.training.pizzaDelivery.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CartItemRepository cartItemRepository;
    private final DealService dealService;

    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.2); // 2%
    private static final BigDecimal DELIVERY_CHARGE = BigDecimal.valueOf(50); // 50 rupees
    @Transactional
    public Checkout saveCheckout(Checkout checkout) {
//        List<CartItem> cartItems = cartItemRepository.findByUser_Id(checkout.getUser().getId());
//        if (cartItems == null || cartItems.isEmpty()) {
//            throw new IllegalStateException("Cart items are empty or null. Cannot proceed to checkout");
//        }
//        checkout.setCartItems(cartItems);
//
//        BigDecimal subtotal = calculateSubtotal(cartItems);
//        checkout.setSubTotal(subtotal);
//
//        BigDecimal totalDiscount = BigDecimal.ZERO;
//        if(checkout.getDeal() != null)
//        {
//            Deal selectedDeal = checkout.getDeal();
//            totalDiscount = calculateTotalDiscount(cartItems, selectedDeal);
//        }
//
//        checkout.setDiscountPrice(totalDiscount);
//
//        BigDecimal grandTotalBeforeTaxesAndDelivery = subtotal.subtract(totalDiscount);
//        BigDecimal grandTotalBeforeTaxes = grandTotalBeforeTaxesAndDelivery.add(DELIVERY_CHARGE);
//        BigDecimal taxes = grandTotalBeforeTaxes.multiply(TAX_RATE);
//
//        BigDecimal grandTotal = grandTotalBeforeTaxes.add(taxes);
//        checkout.setGrandTotal(grandTotal);
        BigDecimal discountPrice = BigDecimal.ZERO;
        discountPrice = setCheckoutTotals(checkout);
        if(discountPrice == BigDecimal.ZERO)
        {
            throw new IllegalStateException("Deal not applicable");
        }
        return checkoutRepository.save(checkout);
    }
    private BigDecimal setCheckoutTotals(Checkout checkout) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(checkout.getUser().getId());
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalStateException("Cart items are empty or null. Cannot proceed to checkout");
        }
        checkout.setCartItems(cartItems);

        BigDecimal subtotal = calculateSubtotal(cartItems);
        checkout.setSubTotal(subtotal);

        BigDecimal totalDiscount = BigDecimal.ZERO;
        if(checkout.getDeal() != null)
        {
            Deal selectedDeal = checkout.getDeal();
            totalDiscount = calculateTotalDiscount(cartItems, selectedDeal);
        }

        checkout.setDiscountPrice(totalDiscount);

        BigDecimal grandTotalBeforeTaxesAndDelivery = subtotal.subtract(totalDiscount);
        BigDecimal grandTotalBeforeTaxes = grandTotalBeforeTaxesAndDelivery.add(DELIVERY_CHARGE);
        BigDecimal taxes = grandTotalBeforeTaxes.multiply(TAX_RATE);

        BigDecimal grandTotal = grandTotalBeforeTaxes.add(taxes);
        checkout.setGrandTotal(grandTotal);
        return totalDiscount;
    }
    private BigDecimal calculateTotalDiscount(List<CartItem> cartItems, Deal selectedDeal) {
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            BigDecimal itemPrice = cartItem.getItem_price();
            BigDecimal discount = dealService.calculateDiscount(cartItem, selectedDeal, itemPrice);
            totalDiscount = totalDiscount.add(discount);
        }

        return totalDiscount;
    }

    public Checkout getCheckoutsByUserId(Long userId) {
        return checkoutRepository.findByUserId(userId);
    }

    public void deleteCheckout(Long checkoutId) {
        checkoutRepository.deleteById(checkoutId);
    }

    public Checkout getCheckoutById(long checkoutId) {
        return checkoutRepository.findById(checkoutId);
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
