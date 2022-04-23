package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.repository.DishRepository;
import ru.example.java.demo.repository.MenuRepository;
import ru.example.java.demo.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.Collection;
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Transactional
    public Menu create(Long restaurantId, Menu menu) {
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        menu.getDishs().forEach(e -> e.setMenu(menu));
        return menuRepository.save(menu);
    }

    @Transactional
    public Menu update(Menu menu) {
        menu.getDishs().forEach(e -> e.setMenu(menu));
        return menuRepository.save(menu);
    }

    @Transactional
    public void delete(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    public Collection<Menu> findAll() {
        return menuRepository.findAll();
    }


    public Menu findById(Long id) {

        return menuRepository.findById(id).orElseThrow();
    }

    public Menu findByRestaurantIdAndDate(Long restaurantId, LocalDate date) {
        return menuRepository.findByRestaurantIdAndDate(restaurantId, date).orElseThrow(()-> new RuntimeException("null") );
    }

}
