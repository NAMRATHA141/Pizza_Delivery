package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.*;
import com.tc.training.pizzaDelivery.service.Impl.MenuServiceImpl;

import java.util.List;

public interface MenuService {

    Menu addMenuItem(Menu menu);

    void deleteMenuItem(Long menuId);

    List<Menu> getMenuItemsByLocation(String location);
    List<Product> getProductsByLocation(String location);
    List<Size> getSizesByLocation(String location);
    List<Crust> getCrustsByLocation(String location);
    List<Toppings> getToppingsByLocation(String location);

}
