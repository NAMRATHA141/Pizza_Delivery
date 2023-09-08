package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.Product;

import java.util.List;

public interface SearchService {
    List<Product> searchProducts(String keyword);
}
