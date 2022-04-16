package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.convertor.MenuConvertor;
import ru.example.java.demo.dto.MenuDto;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.service.MenuService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus/")
public class MenuController {
    public final MenuService menuService;
    public final MenuConvertor menuConvertor;


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
    public Collection<MenuDto> findAll() {
        return menuService.findAll().stream()
                .peek(e -> System.out.println(e.toString()))
                .map(menuConvertor::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public Menu findById(@PathVariable Long id) {
        return menuService.findById(id);
    }






}
