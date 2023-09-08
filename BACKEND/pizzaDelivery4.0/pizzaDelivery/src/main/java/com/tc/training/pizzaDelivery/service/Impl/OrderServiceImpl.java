package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.enums.OrderStatus;
import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.model.Product;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.repository.CartItemToppingsRepository;
import com.tc.training.pizzaDelivery.repository.OrderRepository;
import com.tc.training.pizzaDelivery.repository.ProductRepository;
import com.tc.training.pizzaDelivery.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemToppingsRepository cartItemToppingsRepository;

    public Order saveOrder(Order order) {
        order.setTimestamp(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(newStatus);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Order with ID " + orderId + " not found.");
        }
    }
    public void deleteOrder(Long orderId) { orderRepository.deleteById(orderId);}

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserLocation(String outletLocation) {
        return orderRepository.findByOutletLocation(outletLocation);
    }
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByUser_Id(customerId);
    }

    public List<Object[]> getTopSellingProductsByOutletLocation(String outletLocation) {
        return orderRepository.findTopSellingProductsByOutletLocation(outletLocation);
    }


}
