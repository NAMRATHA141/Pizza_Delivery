package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.Menu;
import com.tc.training.pizzaDelivery.model.Outlet;
import com.tc.training.pizzaDelivery.model.User;
import com.tc.training.pizzaDelivery.service.Impl.OrderServiceImpl;
import com.tc.training.pizzaDelivery.service.OutletService;
import com.tc.training.pizzaDelivery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/outlets")
public class OutletController {
    private final OutletService outletService;
    private final UserService userService;


    @PostMapping("/add-outlet")
    public Outlet createOutlet(@RequestBody Outlet outlet) {
        User user = userService.getUserById(outlet.getUser().getId());
        outlet.setLocation(user.getAddress());
        Outlet out = outletService.createOutlet(outlet);
        return new ResponseEntity<>(out, HttpStatus.CREATED).getBody();
    }


    @GetMapping("/outlet/{userId}")  // New endpoint
    public Outlet getOutletByUserId(@PathVariable Long userId) {
        return outletService.getOutletByUserId(userId);
    }

    @GetMapping
    public List<Outlet> getAllOutlets() {
        return outletService.getAllOutlets();
    }

    @PutMapping("/{id}")
    public Outlet updateOutlet(@PathVariable Long id, @RequestBody Outlet outlet) {
        return outletService.updateOutlet(id, outlet);
    }

    @DeleteMapping("/{id}")
    public void deleteOutlet(@PathVariable Long id) {
        outletService.deleteOutlet(id);
    }

    @GetMapping("/totalsales") // Updated the path here
    public BigDecimal calculateTotalSalesForOutletInDateRange(
            @RequestParam("outletLocation") String outletLocation,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return outletService.calculateTotalSalesForOutletInDateRange(outletLocation, startDate, endDate);
    }
}
