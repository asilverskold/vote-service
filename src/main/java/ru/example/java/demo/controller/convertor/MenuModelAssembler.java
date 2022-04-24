package ru.example.java.demo.controller.convertor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.example.java.demo.controller.MenuController;
import ru.example.java.demo.controller.RestaurantController;
import ru.example.java.demo.model.Menu;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class MenuModelAssembler implements RepresentationModelAssembler<Menu, EntityModel<Menu>> {

    @Override
    public EntityModel<Menu> toModel(Menu menu) {

        EntityModel<Menu> mMenu = EntityModel.of(
                menu,
                linkTo(methodOn(MenuController.class).one(menu.getId()))
                               .withSelfRel(),
                linkTo(methodOn(MenuController.class).all()).withRel("menus"),
                linkTo(methodOn(RestaurantController.class).one(menu.getRestaurant().getId())).withRel("restaurant"));

        return mMenu;
    }
}
