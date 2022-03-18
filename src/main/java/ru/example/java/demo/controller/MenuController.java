package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.model.poll.Poll;
import ru.example.java.demo.service.MenuService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {
    public final MenuService menuService;

    @PostMapping("/menus")
    public Menu create(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping("/menus")
    public Menu update(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/menus")
    public void delete(@RequestParam Long menuId) {
        menuService.deleteMenu(menuId);
    }

    @GetMapping("/menus")
    public Collection<Menu> findAll() {
        return menuService.findAllMenus();
    }




}
