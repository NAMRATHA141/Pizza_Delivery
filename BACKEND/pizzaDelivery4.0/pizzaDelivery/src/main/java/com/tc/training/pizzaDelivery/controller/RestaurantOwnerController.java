package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.Menu;
import com.tc.training.pizzaDelivery.model.Order;
import com.tc.training.pizzaDelivery.service.MenuService;
import com.tc.training.pizzaDelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Owner")
public class RestaurantOwnerController {

    private final MenuService menuService;
    private final OrderService orderService;

    @Autowired
    public RestaurantOwnerController(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }

    // Get all orders by location
    @GetMapping("/orders/by-location")
    public ResponseEntity<List<Order>> getOrdersByLocation(@RequestParam String location) {
        List<Order> orders = orderService.getOrdersByUserLocation(location);
        return ResponseEntity.ok(orders);
    }

    // Add a menu item for a specific location
    @PostMapping("/menu/add-by-location")
    public ResponseEntity<Menu> addMenuItemsByLocation(
            @RequestParam String location,
            @RequestBody Menu menu) {
        menu.setOutletAddress(location); // Set the location for the menu
        Menu addedMenu = menuService.addMenuItem(menu);
        return new ResponseEntity<>(addedMenu, HttpStatus.CREATED);
    }

    // Delete a menu item for a specific location
    @DeleteMapping("/menu/delete/{menuId}")
    public ResponseEntity<Void> deleteMenuItemForLocation(@PathVariable Long menuId) {
        menuService.deleteMenuItem(menuId);
        return ResponseEntity.noContent().build();
    }
}
