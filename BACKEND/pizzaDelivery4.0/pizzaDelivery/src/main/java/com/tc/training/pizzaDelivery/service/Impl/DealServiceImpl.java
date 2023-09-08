package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Deal;
import com.tc.training.pizzaDelivery.repository.DealRepository;
import com.tc.training.pizzaDelivery.service.CartItemService;
import com.tc.training.pizzaDelivery.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final CartItemService cartItemService;

    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    public Deal getDealById(Long id) {
        Deal deal = dealRepository.getDealById(id);
        if (deal == null) {
            throw new IllegalStateException("Deal with ID " + id + " not found");
        }
        return deal;
    }

    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    public void deleteDeal(Long id) {
        dealRepository.deleteById(id);
    }

    public BigDecimal calculateDiscount(CartItem cartItem, Deal selectedDeal, BigDecimal itemPrice) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        Long cartItemId = cartItem.getProduct().getId();
        List<Deal> deals = dealRepository.findByEligibleItem_Id(cartItemId);

        for (Deal deal : deals) {
            if (deal.getEligibleItem() != null && deal.getEligibleItem().getId().equals(cartItemId)) {
                if (deal.getId().equals(selectedDeal.getId())) {
                    BigDecimal discount = calculateDealDiscount(cartItem, deal, itemPrice);
                    totalDiscount = totalDiscount.add(discount);
                    break;
                }
            }
        }
        return totalDiscount;
    }

    private BigDecimal calculateDealDiscount(CartItem cartItem, Deal deal, BigDecimal itemPrice) {
        BigDecimal discount = BigDecimal.ZERO;

        switch (deal.getType()) {
            case BOGO:
                if (cartItem.getProduct().getId().equals(deal.getEligibleItem().getId())) {
                    discount = BigDecimal.valueOf(0.50);
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

    public List<Deal> getApplicableDealsForCartItem(Long cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        if (cartItem == null) {
            throw new IllegalStateException("Cart item with ID " + cartItemId + " not found.");
        }
        Long productId = cartItem.getProduct().getId();
        List<Deal> deals = dealRepository.findByEligibleItem_Id(productId);
        List<Deal> applicableDeals = new ArrayList<>();

        for (Deal deal : deals) {
            if (deal.getEligibleItem() != null && deal.getEligibleItem().getId().equals(productId)) {
                applicableDeals.add(deal);
            }
        }

        return applicableDeals;
    }
}
