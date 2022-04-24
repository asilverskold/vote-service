package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.controller.convertor.RestaurantModelAssembler;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.service.RestaurantService;
import ru.example.java.demo.service.VoteService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final VoteService voteService;
    private  final RestaurantModelAssembler assembler;



    @PostMapping
    public EntityModel<Restaurant> create(@RequestBody Restaurant restaurant) {
        return assembler.toModel(restaurantService.create(restaurant)) ;
    }

    @PutMapping
    public EntityModel<Restaurant> update(@RequestBody Restaurant restaurant){
        return assembler.toModel(restaurantService.update(restaurant));
    }

    @DeleteMapping
    public void delete(@RequestParam Long restaurantId) {
        restaurantService.delete(restaurantId);
    }

    @GetMapping
    public Collection<EntityModel<Restaurant>> all() {
        return restaurantService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public EntityModel<Restaurant> one(@PathVariable Long id) {
        return assembler.toModel(restaurantService.findById(id));
    }

    @GetMapping("{id}/menu")
    public Menu getMenu(@PathVariable Long id, @RequestParam LocalDate date) {
        return restaurantService.findMenuByRestaurantIdAndDate(id, date);
    }

}
