package ru.example.java.demo.convertor;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.example.java.demo.controller.RestaurantController;
import ru.example.java.demo.model.Restaurant;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class RestaurantModelAssembler implements RepresentationModelAssembler<Restaurant, EntityModel<Restaurant>> {

    @Override
    public EntityModel<Restaurant> toModel(Restaurant restaurant) {

        EntityModel<Restaurant> rModel = EntityModel.of(
                restaurant,
                linkTo(methodOn(RestaurantController.class).one(restaurant.getId()))
                        .withSelfRel(),
                linkTo(methodOn(RestaurantController.class).all())
                        .withRel("restaurants"),
                linkTo(methodOn(RestaurantController.class).getMenu(restaurant.getId(), LocalDate.now()))
                        .withRel("menu"));


        return rModel;
    }
}
