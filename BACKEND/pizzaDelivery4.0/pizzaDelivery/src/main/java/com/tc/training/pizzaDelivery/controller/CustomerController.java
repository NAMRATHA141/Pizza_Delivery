package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.Menu;
import com.tc.training.pizzaDelivery.service.MenuService;
import com.tc.training.pizzaDelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final MenuService menuService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }

    @PostMapping("menu/by-location")
    public List<Menu> getMenuItemsByLocation(@RequestBody Map<String, String> requestBody) {
        String location = requestBody.get("location");
        List<Menu> menuItems = menuService.getMenuItemsByLocation(location);
        return menuItems;
    }
}