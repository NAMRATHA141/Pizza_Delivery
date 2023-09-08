package com.tc.training.pizzaDelivery.repository;


import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByLocation(String location);
    List<Order> findByUser_Id(Long customerId);
    List<Order> findByOutletLocation(String outletLocation);

    @Query(
            value = "SELECT p.id, p.name, p.price " +
                    "FROM PRODUCT p " +
                    "WHERE p.id IN (" +
                    "  SELECT ci.product_id " +
                    "  FROM checkout_cart cc " +
                    "  JOIN cart_item ci ON cc.cart_item_id = ci.id " +
                    "  JOIN ORDER_TABLE o ON cc.checkout_id = o.checkout_id " +
                    "  WHERE o.outlet_location = :outletLocation" +
                    ") " +
                    "GROUP BY p.id " +
                    "ORDER BY COUNT(p.id) DESC " +
                    "LIMIT 5",
            nativeQuery = true
    )
    List<Object[]> findTopSellingProductsByOutletLocation(@Param("outletLocation") String outletLocation);

    List<Order> findByOutletLocationAndTimestampBetween(String outletLocation, LocalDateTime localDateTime, LocalDateTime localDateTime1);

    @Query(
            value = "SELECT COALESCE(SUM(cc.grand_total), 0) " +
                    "FROM checkout cc " +
                    "INNER JOIN ORDER_TABLE o ON cc.id = o.checkout_id " +
                    "WHERE o.outlet_location = :outletLocation " +
                    "  AND o.timestamp BETWEEN :startDate AND :endDate",
            nativeQuery = true
    )
    BigDecimal calculateTotalSalesForOutletInDateRange(@Param("outletLocation") String outletLocation, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
