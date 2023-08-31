package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.Deal;
import com.tc.training.pizzaDelivery.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/deals")
public class DealController {

    private final DealService dealService;
    @GetMapping
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = dealService.getAllDeals();
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/deal/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable Long id) {
        Optional<Deal> dealOptional = dealService.getDealById(id);
        return dealOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//    @PostMapping("/deal/add")
//    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) {
//        Deal createdDeal = dealService.createDeal(deal);
//        return ResponseEntity.ok(createdDeal);
//    }
//
//    @DeleteMapping("/deal/{id}")
//    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
//        dealService.deleteDeal(id);
//        return ResponseEntity.noContent().build();
//    }
}
