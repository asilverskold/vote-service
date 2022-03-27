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

    @PostMapping()
    public Menu create(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping()
    public Menu update(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping()
    public void delete(@RequestParam Long menuId) {
        menuService.deleteMenu(menuId);
    }

    @GetMapping()
    public Collection<Menu> findAll() {
        return menuService.findAllMenus();
    }

    @GetMapping("{id}")
    public Menu findById(@PathVariable Long id) {
        return menuService.findById(id);
    }






}
