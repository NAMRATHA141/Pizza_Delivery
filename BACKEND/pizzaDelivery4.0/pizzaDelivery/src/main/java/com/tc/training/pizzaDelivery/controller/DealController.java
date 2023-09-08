package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Deal;
import com.tc.training.pizzaDelivery.service.CartItemService;
import com.tc.training.pizzaDelivery.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;
    private final CartItemService cartItemService;
    @GetMapping
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = dealService.getAllDeals();
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/deal/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable Long id) {
        Deal deal = dealService.getDealById(id);
        if (deal != null) {
            return ResponseEntity.ok(deal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/applicable-deals/{cartItemId}")
    public ResponseEntity<List<Deal>> getApplicableDealsForCartItem(@PathVariable Long cartItemId) {
        List<Deal> applicableDeals = dealService.getApplicableDealsForCartItem(cartItemId);
        return ResponseEntity.ok(applicableDeals);
    }

    @GetMapping("/deal/discount")
    public ResponseEntity<BigDecimal> calculateDiscountPrice(
            @RequestParam Long cartItemId,
            @RequestParam Long selectedDealId,
            @RequestParam BigDecimal itemPrice
    ) {
        // Load the CartItem, selected Deal, and itemPrice from your service/repository

        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        Deal selectedDeal = dealService.getDealById(selectedDealId);

        if (cartItem == null || selectedDeal == null) {
            return ResponseEntity.badRequest().build();
        }

        // Calculate the discount price using your existing logic
        BigDecimal discountPrice = dealService.calculateDiscount(cartItem, selectedDeal, itemPrice);

        return ResponseEntity.ok(discountPrice);
    }
}
