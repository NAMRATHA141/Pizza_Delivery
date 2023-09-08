package com.tc.training.pizzaDelivery.repository;

import com.tc.training.pizzaDelivery.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUser_Id(Long userId);
    @Query("SELECT c.item_price FROM CART_ITEM c WHERE c.id = :id")
    BigDecimal findPriceById(@Param("id") Long id);
//    @Query("SELECT c.product, SUM(c.quantity) AS totalQuantity " +
//            "FROM CartItem c " +
//            "GROUP BY c.product " +
//            "ORDER BY totalQuantity DESC")
//    List<Object[]> findTop5ProductsByQuantity();

}
