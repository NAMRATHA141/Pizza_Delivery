package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Toppings;
import com.tc.training.pizzaDelivery.repository.*;
import com.tc.training.pizzaDelivery.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CrustRepository crustRepository;
    private final SizeRepository sizeRepository;
    private final ToppingsRepository toppingsRepository;

    private final CartItemToppingsRepository cartItemToppingsRepository;



    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, ProductRepository productRepository, CrustRepository crustRepository, SizeRepository sizeRepository, ToppingsRepository toppingsRepository, CartItemToppingsRepository cartItemToppingsRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.crustRepository = crustRepository;
        this.sizeRepository = sizeRepository;
        this.toppingsRepository = toppingsRepository;
        this.cartItemToppingsRepository = cartItemToppingsRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }

    public CartItem saveCartItem(CartItem cartItem) {
        BigDecimal calculatedPrice = calculateCartItemPrice(cartItem);
        cartItem.setItem_price(calculatedPrice);
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        cartItemToppingsRepository.deleteById(cartItemId);
        cartItemRepository.deleteById(cartItemId);

    }

    public List<CartItem> getCartItemsByCustomerId(Long user_id) {
        return cartItemRepository.findByUser_Id(user_id);
    }

    private BigDecimal calculateCartItemPrice(CartItem cartItem) {
        BigDecimal basePrice = productRepository.findPriceById(cartItem.getProduct().getId());
        BigDecimal crustPrice = crustRepository.findPriceById(cartItem.getCrust().getId());
        BigDecimal sizePrice = sizeRepository.findPriceById(cartItem.getSize().getId());
        BigDecimal toppingsPrice = calculateToppingsPrice(cartItem.getToppings());
        int quantity = cartItem.getQuantity();

        if (basePrice == null) {
            throw new IllegalArgumentException("No product is added to the cart");
        }
        BigDecimal totalPrice = basePrice
                .add(crustPrice)
                .multiply(sizePrice)
                .add(toppingsPrice)
                .multiply(BigDecimal.valueOf(quantity));

        return totalPrice;
    }

    private BigDecimal calculateToppingsPrice(List<Toppings> toppings) {
        BigDecimal toppingsPrice = BigDecimal.ZERO;
        for (Toppings topping : toppings) {
                toppingsPrice = toppingsPrice.add(toppingsRepository.findPriceById(topping.getId()));

        }
        return toppingsPrice;
    }

}


