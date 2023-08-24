package com.tc.training.pizzaDelivery.controller;

import com.tc.training.pizzaDelivery.model.Menu;
import com.tc.training.pizzaDelivery.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/add")
    public ResponseEntity<Menu> addMenuItem(@RequestBody Menu menu) {
        Menu addedMenu = menuService.addMenuItem(menu);
        return new ResponseEntity<>(addedMenu, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long menuId) {
        menuService.deleteMenuItem(menuId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/by-location")
    public List<Menu> getMenuItemsByLocation(@RequestBody Map<String, String> requestBody) {
        String location = requestBody.get("location");
        List<Menu> menuItems = menuService.getMenuItemsByLocation(location);
        return menuItems;
    }

}
