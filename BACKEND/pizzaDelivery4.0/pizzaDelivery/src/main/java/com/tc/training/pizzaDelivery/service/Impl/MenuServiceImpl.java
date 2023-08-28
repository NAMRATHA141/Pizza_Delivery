package com.tc.training.pizzaDelivery.service.Impl;


import com.tc.training.pizzaDelivery.model.*;
import com.tc.training.pizzaDelivery.repository.MenuRepository;
import com.tc.training.pizzaDelivery.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public Menu addMenuItem(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenuItem(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    public List<Menu> getMenuItemsByLocation(String location) {
        return menuRepository.findByOutletAddress(location);
    }


    public List<Product> getProductsByLocation(String location) {
        List<Menu> menuItems = menuRepository.findByOutletAddress(location);
        return extractProducts(menuItems);
    }

    public List<Size> getSizesByLocation(String location) {
        List<Menu> menuItems = menuRepository.findByOutletAddress(location);
        return extractSizes(menuItems);
    }

    public List<Crust> getCrustsByLocation(String location) {
        List<Menu> menuItems = menuRepository.findByOutletAddress(location);
        return extractCrusts(menuItems);
    }

    public List<Toppings> getToppingsByLocation(String location) {
        List<Menu> menuItems = menuRepository.findByOutletAddress(location);
        return extractToppings(menuItems);
    }

    private List<Product> extractProducts(List<Menu> menuItems) {
        return menuItems.stream()
                .flatMap(menu -> menu.getProducts().stream())
                .collect(Collectors.toList());
    }

    private List<Size> extractSizes(List<Menu> menuItems) {
        return menuItems.stream()
                .flatMap(menu -> menu.getSize().stream())
                .collect(Collectors.toList());
    }

    private List<Crust> extractCrusts(List<Menu> menuItems) {
        return menuItems.stream()
                .flatMap(menu -> menu.getCrust().stream())
                .collect(Collectors.toList());
    }

    private List<Toppings> extractToppings(List<Menu> menuItems) {
        return menuItems.stream()
                .flatMap(menu -> menu.getToppings().stream())
                .collect(Collectors.toList());
    }
}
