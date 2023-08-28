package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Menu;
import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.service.CartItemService;
import com.tc.training.pizzaDelivery.service.MenuService;
import com.tc.training.pizzaDelivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/customer")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CustomerController {

    private final MenuService menuService;
    private final OrderService orderService;
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;

    @PostMapping("/menu/by-location")
    public List<Menu> getMenuItemsByLocation(@RequestBody Map<String, String> requestBody) {
        String location = requestBody.get("location");
        List<Menu> menuItems = menuService.getMenuItemsByLocation(location);
        return menuItems;
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

    @PostMapping("/order/add")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(order.getUser().getId());
        Order savedOrder = orderService.saveOrder(order);
        for (CartItem cartItem : cartItems) {
            cartItem.setUser(null);
            cartItemRepository.save(cartItem);
        }
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    @DeleteMapping("/order/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}