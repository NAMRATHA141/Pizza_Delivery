package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Checkout;
import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.model.User;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.repository.CartItemToppingsRepository;
import com.tc.training.pizzaDelivery.repository.CheckoutRepository;
import com.tc.training.pizzaDelivery.repository.OrderRepository;
import com.tc.training.pizzaDelivery.service.CheckoutService;
import com.tc.training.pizzaDelivery.service.OrderService;
import com.tc.training.pizzaDelivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    private final CartItemToppingsRepository cartItemToppingsRepository;
    @Transactional
    @PostMapping("/order/add")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(order.getUser().getId());
        User user = userService.getUserById(order.getUser().getId());
        order.setLocation(user.getAddress());
        Order savedOrder = orderService.saveOrder(order);
        for (CartItem cartItem : cartItems) {
            cartItem.setUser(null);
            cartItemRepository.save(cartItem);
        }
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
//    @DeleteMapping("/order/delete/{orderId}")
//    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
//        orderService.deleteOrder(orderId);
//        return ResponseEntity.noContent().build();
//    }

//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderService.getAllOrders();
//        return ResponseEntity.ok(orders);
//    }

    @GetMapping("/by-outletLocation")
    public ResponseEntity<List<Order>> getOrdersByLocation(@RequestParam String outletLocation) {
        List<Order> orders = orderService.getOrdersByUserLocation(outletLocation);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
}
