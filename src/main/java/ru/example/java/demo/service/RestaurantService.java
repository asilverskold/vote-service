package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.repository.DishRepository;
import ru.example.java.demo.repository.MenuRepository;
import ru.example.java.demo.repository.RestaurantRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;

    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void deleteRestaurant(Long restaurantId){
        restaurantRepository.deleteById(restaurantId);
    }

    public Collection<Restaurant> findAllRestaurant(){
       return restaurantRepository.findAll();
    }

    public Restaurant findRestaurantById(Long restaurantId){
       return restaurantRepository.findById(restaurantId)
               .orElseThrow();
    }

    @Transactional
    public void setMenuDayToRestaurant(Menu menu, Long restaurantId){
        Restaurant restaurant = this.findRestaurantById(restaurantId);
        if(menu.getRestaurant()!= null){throw new RuntimeException("");}
        restaurant.setMenu(menu);
        this.updateRestaurant(restaurant);
    }


}
