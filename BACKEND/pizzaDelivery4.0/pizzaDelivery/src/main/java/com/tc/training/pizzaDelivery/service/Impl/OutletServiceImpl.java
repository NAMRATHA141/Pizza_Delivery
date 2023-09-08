package com.tc.training.pizzaDelivery.service.Impl;

import com.tc.training.pizzaDelivery.model.Outlet;
import com.tc.training.pizzaDelivery.repository.OrderRepository;
import com.tc.training.pizzaDelivery.repository.OutletRepository;
import com.tc.training.pizzaDelivery.service.OutletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class OutletServiceImpl implements OutletService {
    private final OrderRepository orderRepository;
    private final OutletRepository outletRepository;

    public Outlet createOutlet(Outlet outlet) {
        return outletRepository.save(outlet);
    }

    public Outlet getOutletById(Long id) {
        return outletRepository.findById(id).orElse(null);
    }

    public Outlet getOutletByUserId(Long userId) {
        return outletRepository.findByUserId(userId);
    }

    public List<Outlet> getAllOutlets() {
        return outletRepository.findAll();
    }

    public Outlet updateOutlet(Long id, Outlet outlet) {
        outlet.setId(id); // Ensure the outlet ID matches the path variable ID

        return outletRepository.save(outlet);
    }

    public void deleteOutlet(Long id) {
        outletRepository.deleteById(id);
    }
    public BigDecimal calculateTotalSalesForOutletInDateRange(String outletLocation, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return orderRepository.calculateTotalSalesForOutletInDateRange(outletLocation, startDateTime, endDateTime);
    }

}
