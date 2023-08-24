package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    void deleteOrder(Long orderId);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserLocation(String location);
}
