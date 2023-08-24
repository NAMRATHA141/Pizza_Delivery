package com.tc.training.pizzaDelivery.service;

import com.tc.training.pizzaDelivery.model.Menu;
import com.tc.training.pizzaDelivery.service.Impl.MenuServiceImpl;

import java.util.List;

public interface MenuService {

    Menu addMenuItem(Menu menu);

    void deleteMenuItem(Long menuId);

    List<Menu> getMenuItemsByLocation(String location);
}
