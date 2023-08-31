package com.tc.training.pizzaDelivery.model;

import com.tc.training.pizzaDelivery.enums.DealType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "DEAL")
@Data
@DynamicUpdate
@DynamicInsert
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal discountAmount;
    private double percentageOff;
    private BigDecimal thresholdAmount;
    private String category;

    @Enumerated(EnumType.STRING)
    private DealType type;

    @ManyToOne
    @JoinColumn(name = "eligible_item_id")
    private Product eligibleItem;

}
