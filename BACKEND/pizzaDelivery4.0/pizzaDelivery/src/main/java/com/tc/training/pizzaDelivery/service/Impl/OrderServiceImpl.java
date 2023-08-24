package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.repository.CartItemRepository;
import com.tc.training.pizzaDelivery.repository.CartItemToppingsRepository;
import com.tc.training.pizzaDelivery.repository.OrderRepository;
import com.tc.training.pizzaDelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;

    }

    public Order saveOrder(Order order) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(order.getUser().getId());
        BigDecimal calculatedPrice = calculateTotalPrice(order);
        order.setTotalPrice(calculatedPrice);
        order.setCartItems(cartItems);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserLocation(String location) {
        return orderRepository.findByLocation(location);
    }

    private BigDecimal calculateTotalPrice(Order order) {
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(order.getUser().getId());
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            BigDecimal itemPrice = cartItemRepository.findPriceById(cartItem.getId());
            totalPrice = totalPrice.add(itemPrice);
        }

        return totalPrice;
    }


}
