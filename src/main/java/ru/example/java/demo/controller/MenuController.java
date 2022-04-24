package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.controller.convertor.MenuModelAssembler;
import ru.example.java.demo.model.Menu;
import ru.example.java.demo.service.MenuService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus/")
public class MenuController {
    private final MenuService menuService;
    private final MenuModelAssembler assembler;

    @Secured("ROLE_ADMIN")
    @PostMapping()
    public EntityModel<Menu> create(@RequestParam Long restaurantId, @RequestBody Menu menu) {
        return assembler.toModel(menuService.create(restaurantId, menu));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping()
    public EntityModel<Menu> update(@RequestBody Menu menu){

        return assembler.toModel(menuService.update(menu));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping()
    public void delete(@RequestParam Long menuId) {
        menuService.delete(menuId);
    }
    @GetMapping()

    @Secured("ROLE_ADMIN")
    public Collection<EntityModel<Menu>> all() {

        return menuService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }
    @Secured("ROLE_USER")
    @GetMapping("{id}")
    public EntityModel<Menu> one(@PathVariable Long id) {
        return assembler.toModel(menuService.findById(id));
    }






}
