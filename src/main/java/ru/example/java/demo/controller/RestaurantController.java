package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.service.RestaurantService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurants")
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant) ;
    }

    @PutMapping("/restaurants")
    public Restaurant update(@RequestBody Restaurant restaurant){
        return restaurantService.updateRestaurant(restaurant);
    }

    @DeleteMapping("/restaurants")
    public void delete(@RequestParam Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }

    @GetMapping("/restaurants")
    public Collection<Restaurant> findAll() {
        return restaurantService.findAllRestaurant();
    }


}
