package com.tc.training.pizzaDelivery.repository;


import com.tc.training.pizzaDelivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByLocation(String location);
    List<Order> findByUser_Id(Long customerId);
    List<Order> findByOutletLocation(String outletLocation);

}
