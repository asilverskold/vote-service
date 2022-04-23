package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.convertor.MenuConvertor;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.service.MenuService;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus/")
public class MenuController {
    public final MenuService menuService;



    @PostMapping()
    public Menu create(@RequestParam Long restaurantId, @RequestBody Menu menu) {




        return menuService.create(restaurantId, menu);

    }

    @PutMapping()
    public Menu update(@RequestBody Menu menu){
        return menuService.update(menu);
    }

    @DeleteMapping()
    public void delete(@RequestParam Long menuId) {
        menuService.delete(menuId);
    }

    @GetMapping()
    public Collection<Menu> all() {
        return menuService.findAll();
    }

    @GetMapping("{id}")
    public Menu one(@PathVariable Long id) {
        return menuService.findById(id);
    }






}
