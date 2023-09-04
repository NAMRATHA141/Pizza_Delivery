package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.model.Product;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    void deleteOrder(Long orderId);

    List<Order> getAllOrders();
    List<Object> getTopSellingProductsByOutletLocation(String outletLocation);
    List<Order> getOrdersByUserLocation(String location);
    List<Order> getOrdersByCustomerId(Long customerId);
}
