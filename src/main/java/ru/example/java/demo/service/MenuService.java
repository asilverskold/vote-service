package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.repository.DishRepository;
import ru.example.java.demo.repository.MenuRepository;
import ru.example.java.demo.repository.RestaurantRepository;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Transactional
    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    @Transactional
    public Menu updateMenu(Menu menu){
        return menuRepository.save(menu);
    }

    @Transactional
    public void deleteMenu(Long menuId){
        menuRepository.deleteById(menuId);
    }

    @Transactional
    public void addDishToMenu(Long menuId, Dish dish){
       Menu menu = menuRepository.findById(menuId).orElseThrow();

       dish.setMenu(menu);
       dish.setRestaurant(menu.getRestaurant());
       dishRepository.save(dish);
    }

    @Transactional
    public void deleteDishToMenu(Long dishId, Long menuId) {
       Dish dish = dishRepository.findByIdAndMenu(dishId,menuId).orElseThrow();
       dish.setMenu(null);
       dishRepository.save(dish);
    }






}
