package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.service.MenuService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus/")
public class MenuController {
    public final MenuService menuService;

    @PostMapping("{restaurantId}")
    public Menu create(@PathVariable Long restaurantId, @RequestBody Menu menu) {

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
    public Collection<Menu> findAll() {
        return menuService.findAll();
    }

    @GetMapping("{id}")
    public Menu findById(@PathVariable Long id) {
        return menuService.findById(id);
    }






}
