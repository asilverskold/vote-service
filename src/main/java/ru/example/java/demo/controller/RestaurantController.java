package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.dto.VoteRequest;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.service.RestaurantService;
import ru.example.java.demo.service.VoteService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final VoteService voteService;

    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return restaurantService.create(restaurant) ;
    }

    @PutMapping
    public Restaurant update(@RequestBody Restaurant restaurant){
        return restaurantService.update(restaurant);
    }

    @DeleteMapping
    public void delete(@RequestParam Long restaurantId) {
        restaurantService.delete(restaurantId);
    }

    @GetMapping
    public Collection<Restaurant> findAll() {
        return restaurantService.findAll();
    }

    @GetMapping("{id}")
    public Restaurant findById(@PathVariable Long restaurantId) {
        return restaurantService.findById(restaurantId);
    }

    @PostMapping("{id}/menu")
    public void addMenu(@PathVariable Long restaurantId, @RequestBody Menu menu) {
        restaurantService.addMenuToRestaurant(restaurantId,menu);
    }

   /* @DeleteMapping("/restaurants/{id}/menu")
    public void deleteMenu(@RequestParam Long restaurantId, @RequestParam Long menuId) {

    }*/

    @PostMapping("{Id}/vote")
    public void addPollOption(@PathVariable Long id,@PathVariable Long userId) {
     voteService.vote(id,userId);
    }

}
