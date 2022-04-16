package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.repository.DishRepository;
import ru.example.java.demo.repository.MenuRepository;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dishes/")
public class DishController {

    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

    @PostMapping()
    @Transactional
    public Dish create(@RequestParam Long menuId, @RequestBody Dish dish) {
        //Menu menu = menuRepository.findById(menuId).orElseThrow();
        if (dish.getId() != null) throw new RuntimeException("");
        dish.setMenu(menuRepository.getById(menuId));
        return dishRepository.save(dish);
    }
    @Transactional
    @PutMapping()
    public Dish update(@RequestBody Dish dish){
        return dishRepository.save(dish);
    }
    @Transactional
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
