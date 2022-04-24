package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.convertor.MenuConvertor;
import ru.example.java.demo.convertor.MenuModelAssembler;
import ru.example.java.demo.model.Dish;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.service.MenuService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus/")
public class MenuController {
    private final MenuService menuService;
    private final MenuModelAssembler assembler;



    @PostMapping()
    public EntityModel<Menu> create(@RequestParam Long restaurantId, @RequestBody Menu menu) {
        return assembler.toModel(menuService.create(restaurantId, menu));
    }

    @PutMapping()
    public EntityModel<Menu> update(@RequestBody Menu menu){

        return assembler.toModel(menuService.update(menu));
    }

    @DeleteMapping()
    public void delete(@RequestParam Long menuId) {
        menuService.delete(menuId);
    }

    @GetMapping()
    public Collection<EntityModel<Menu>> all() {

        return menuService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public EntityModel<Menu> one(@PathVariable Long id) {
        return assembler.toModel(menuService.findById(id));
    }






}
