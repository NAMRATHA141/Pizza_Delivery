package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.CartItem;
import com.tc.training.pizzaDelivery.model.Product;
import com.tc.training.pizzaDelivery.service.CartItemService;
import com.tc.training.pizzaDelivery.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService searchService; // Inject your ProductService

    @GetMapping("/product")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(name = "keyword") String keyword) {
        // Implement the search logic here using the 'keyword'
        List<Product> searchResults = searchService.searchProducts(keyword);

        return ResponseEntity.ok(searchResults);
    }
}
