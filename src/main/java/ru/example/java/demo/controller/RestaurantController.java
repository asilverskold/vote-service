package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private  final RestaurantModelAssembler assembler;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public EntityModel<Restaurant> create(@RequestBody Restaurant restaurant) {
        return assembler.toModel(restaurantService.create(restaurant)) ;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public EntityModel<Restaurant> update(@RequestBody Restaurant restaurant){
        return assembler.toModel(restaurantService.update(restaurant));
    }
    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public void delete(@RequestParam Long restaurantId) {
        restaurantService.delete(restaurantId);
    }

    @Secured("ROLE_USER")
    @GetMapping
    public Collection<EntityModel<Restaurant>> all() {
        return restaurantService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }
    @Secured("ROLE_USER")
    @GetMapping("{id}")
    public EntityModel<Restaurant> one(@PathVariable Long id) {
        return assembler.toModel(restaurantService.findById(id));
    }
    @Secured("ROLE_USER")
    @GetMapping("{id}/menu")
    public Menu getMenu(@PathVariable Long id, @RequestParam LocalDate date) {
        return restaurantService.findMenuByRestaurantIdAndDate(id, date);
    }

}
