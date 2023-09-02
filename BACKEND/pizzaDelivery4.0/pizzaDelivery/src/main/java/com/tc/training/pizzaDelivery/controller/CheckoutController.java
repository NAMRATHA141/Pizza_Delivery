package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.Checkout;
import com.tc.training.pizzaDelivery.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/add")
    public ResponseEntity<Checkout> createCheckout(@RequestBody Checkout checkout) {
        Checkout createdCheckout = checkoutService.saveCheckout(checkout);
        return new ResponseEntity<>(createdCheckout, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{checkoutId}")
    public ResponseEntity<Void> deleteCheckout(@PathVariable Long checkoutId) {
        checkoutService.deleteCheckout(checkoutId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{checkoutId}")
    public ResponseEntity<Checkout> getCheckoutById(@PathVariable Long checkoutId) {
        Checkout checkout = checkoutService.getCheckoutById(checkoutId);
        if (checkout == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(checkout);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Checkout> getCheckoutsByUserId(@PathVariable Long userId) {
        Checkout checkout = checkoutService.getCheckoutsByUserId(userId);
        if (checkout == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(checkout);
    }
}
