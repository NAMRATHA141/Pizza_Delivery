package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.Outlet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OutletService {
    Outlet createOutlet(Outlet outlet);
    Outlet getOutletById(Long id);
    List<Outlet> getAllOutlets();
    Outlet updateOutlet(Long id, Outlet outlet);
    void deleteOutlet(Long id);
    BigDecimal calculateTotalSalesForOutletInDateRange(String outletLocation, LocalDate startDate, LocalDate endDate);

    Outlet getOutletByUserId(Long userId);
}
