package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CartItem>> getCartItemsByCustomerId(@PathVariable Long customerId) {
        List<CartItem> cartItems = cartItemService.getCartItemsByCustomerId(customerId);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        CartItem savedCartItem = cartItemService.saveCartItem(cartItem);
        return ResponseEntity.ok(savedCartItem);
    }

    @DeleteMapping("/cart/delete/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping
//    public ResponseEntity<List<CartItem>> getAllCartItems() {
//        List<CartItem> cartItems = cartItemService.getAllCartItems();
//        return ResponseEntity.ok(cartItems);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
//        CartItem cartItem = cartItemService.getCartItemById(id);
//        if (cartItem != null) {
//            return ResponseEntity.ok(cartItem);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @PostMapping
//    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
//        CartItem savedCartItem = cartItemService.saveCartItem(cartItem);
//        return ResponseEntity.ok(savedCartItem);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
//        cartItemService.deleteCartItem(id);
//        return ResponseEntity.noContent().build();
//    }


}
