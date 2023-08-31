package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Deal;
import com.tc.training.pizzaDelivery.repository.DealRepository;
import com.tc.training.pizzaDelivery.service.CartItemService;
import com.tc.training.pizzaDelivery.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final CartItemService cartItemService;

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Optional<Deal> getDealById(Long id) {
        return dealRepository.findById(id);
    }

    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }


    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }

    private BigDecimal calculateDiscount(CartItem cartItem, BigDecimal itemPrice) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        Long id = cartItem.getProduct().getId();
        List<Deal> applicableDeals = dealRepository.findByEligibleItem_Id(cartItem.getProduct().getId());
        for (Deal deal : applicableDeals) {
            BigDecimal discount = calculateDealDiscount(cartItem, deal, itemPrice);
            totalDiscount = totalDiscount.add(discount);
        }

        return totalDiscount;
    }

    private BigDecimal calculateDealDiscount(CartItem cartItem, Deal deal, BigDecimal itemPrice) {
        BigDecimal discount = BigDecimal.ZERO;

        switch (deal.getType()) {
            case BOGO:
                if (cartItem.getProduct().getId().equals(deal.getEligibleItem().getId())) {
                           discount = BigDecimal.ZERO;
                }
                break;

            case FLAT_DISCOUNT:
                discount = itemPrice.multiply(BigDecimal.valueOf(deal.getPercentageOff() / 100));
                break;

            case CATEGORY:
                if (cartItem.getProduct().getCategory().equals(deal.getCategory())) {
                    discount = itemPrice.multiply(BigDecimal.valueOf(deal.getPercentageOff() / 100));
                }
                break;

            case THRESHOLD:
                if (itemPrice.compareTo(deal.getThresholdAmount()) >= 0) {
                    discount = itemPrice.multiply(BigDecimal.valueOf(deal.getPercentageOff() / 100));
                }
                break;
        }

        return discount;
    }
}
