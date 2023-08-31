package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.repository.CartItemToppingsRepository;
import com.tc.training.pizzaDelivery.repository.OrderRepository;
import com.tc.training.pizzaDelivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;

    private final CartItemToppingsRepository cartItemToppingsRepository;

    @PostMapping("/order/add")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(order.getUser().getId());
        order.setLocation(order.getUser().getAddress());
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

    @GetMapping("/by-location")
    public ResponseEntity<List<Order>> getOrdersByLocation(@RequestParam String location) {
        List<Order> orders = orderService.getOrdersByUserLocation(location);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
}
