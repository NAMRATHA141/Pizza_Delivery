package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.*;
import com.tc.training.pizzaDelivery.service.MenuService;
import com.tc.training.pizzaDelivery.service.UserService;
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
    private final UserService userService;
    @PostMapping("/add-by-location")
    public ResponseEntity<Menu> addMenuItemsByLocation(@RequestBody Menu menu) {
        User user = userService.getUserById(menu.getUser().getId());
        menu.setOutletAddress(user.getAddress()); // Set the location for the menu
        Menu addedMenu = menuService.addMenuItem(menu);
        return new ResponseEntity<>(addedMenu, HttpStatus.CREATED);
    }

    // Delete a menu item for a specific location
    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<Void> deleteMenuItemForLocation(@PathVariable Long menuId) {
        menuService.deleteMenuItem(menuId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-location")
    public List<Menu> getMenuItemsByLocation(@RequestParam String location) {
        List<Menu> menuItems = menuService.getMenuItemsByLocation(location);
        return menuItems;
    }

    @GetMapping("/by-location/products")
    public List<Product> getProductsByLocation(@RequestParam String location) {
        List<Product> productItems = menuService.getProductsByLocation(location);
        return productItems;
    }
    @GetMapping("/by-location/crusts")
    public List<Crust> getCrustsByLocation(@RequestParam String location) {
        List<Crust> crustItems = menuService.getCrustsByLocation(location);
        return crustItems;
    }

    @GetMapping("/by-location/sizes")
    public List<Size> getSizesByLocation(@RequestParam String location) {
        List<Size> sizeItems = menuService.getSizesByLocation(location);
        return sizeItems;
    }

    @GetMapping("/by-location/toppings")
    public List<Toppings> getToppingsByLocation(@RequestParam String location) {
        List<Toppings> toppingsItems = menuService.getToppingsByLocation(location);
        return toppingsItems;
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> displayProducts() {
        List<Product> products = menuService.displayProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/crusts")
    public ResponseEntity<List<Crust>> displayCrust() {
        List<Crust> crusts = menuService.displayCrust();
        return ResponseEntity.ok(crusts);
    }

    @GetMapping("/size")
    public ResponseEntity<List<Size>> displaySize() {
        List<Size> sizes = menuService.displaySize();
        return ResponseEntity.ok(sizes);
    }

    @GetMapping("/toppings")
    public ResponseEntity<List<Toppings>> displayToppings() {
        List<Toppings> toppings = menuService.displayToppings();
        return ResponseEntity.ok(toppings);
    }



}
