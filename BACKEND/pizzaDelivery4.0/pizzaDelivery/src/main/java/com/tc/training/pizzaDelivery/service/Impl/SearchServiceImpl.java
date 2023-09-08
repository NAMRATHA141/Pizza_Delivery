package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.Product;
import com.tc.training.pizzaDelivery.repository.ProductRepository;
import com.tc.training.pizzaDelivery.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> searchProducts(String keyword)
    {
        List<Product> productList;
        productList = productRepository.findByNameContainingIgnoreCase(keyword);
        productList.addAll(productRepository.findByDescriptionContainingIgnoreCase(keyword));
        return productList;
    }

}
