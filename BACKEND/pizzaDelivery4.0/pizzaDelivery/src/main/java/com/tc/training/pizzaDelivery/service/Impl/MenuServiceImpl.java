package com.tc.training.pizzaDelivery.service.Impl;


import com.tc.training.pizzaDelivery.model.Menu;
import com.tc.training.pizzaDelivery.repository.MenuRepository;
import com.tc.training.pizzaDelivery.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu addMenuItem(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenuItem(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    public List<Menu> getMenuItemsByLocation(String location) {
        return menuRepository.findByOutletAddress(location);
    }

}
