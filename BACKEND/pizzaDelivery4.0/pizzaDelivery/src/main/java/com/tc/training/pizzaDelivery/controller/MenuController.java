package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.*;
import com.tc.training.pizzaDelivery.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

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

    @GetMapping("/menu/by-location")
    public List<Menu> getMenuItemsByLocation(@RequestParam String location) {
        List<Menu> menuItems = menuService.getMenuItemsByLocation(location);
        return menuItems;
    }

    @GetMapping("menu/by-location/products")
    public List<Product> getProductsByLocation(@RequestParam String location) {
        List<Product> productItems = menuService.getProductsByLocation(location);
        return productItems;
    }
    @GetMapping("menu/by-location/crusts")
    public List<Crust> getCrustsByLocation(@RequestParam String location) {
        List<Crust> crustItems = menuService.getCrustsByLocation(location);
        return crustItems;
    }

    @GetMapping("menu/by-location/sizes")
    public List<Size> getSizesByLocation(@RequestParam String location) {
        List<Size> sizeItems = menuService.getSizesByLocation(location);
        return sizeItems;
    }

    @GetMapping("menu/by-location/toppings")
    public List<Toppings> getToppingsByLocation(@RequestParam String location) {
        List<Toppings> toppingsItems = menuService.getToppingsByLocation(location);
        return toppingsItems;
    }


}
