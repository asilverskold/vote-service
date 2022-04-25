package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.repository.DishRepository;
import ru.example.java.demo.repository.MenuRepository;
import ru.example.java.demo.repository.RestaurantRepository;
import ru.example.java.demo.service.exception.MyException;

import java.time.LocalDate;
import java.util.Collection;
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;


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

        return menuRepository.findById(id).orElseThrow(()-> new MyException("Menu not found"+ id, HttpStatus.BAD_REQUEST));
    }

    public Menu findByRestaurantIdAndDate(Long restaurantId, LocalDate date) {
        return menuRepository.findByRestaurantIdAndDate(restaurantId, date).orElseThrow(()-> new MyException("Menu not found"+ date,HttpStatus.BAD_REQUEST) );
    }

}
