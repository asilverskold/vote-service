package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.repository.DishRepository;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dishes")
public class DishController {

    private final DishRepository dishRepository;

    @PostMapping()
    public Dish create(@RequestBody Dish dish) {
        return dishRepository.save(dish);
    }

    @PutMapping()
    public Dish update(@RequestBody Dish dish){
        return dishRepository.save(dish);
    }

    @DeleteMapping()
    public void delete(@RequestParam Long dishId) {
        dishRepository.deleteById(dishId);
    }

    @GetMapping()
    public Collection<Dish> findAll() {
        return dishRepository.findAll();
    }

    @GetMapping("{id}")
    public Dish findById(@PathVariable Long id) {
        return dishRepository.findById(id).orElseThrow();
    }
}
