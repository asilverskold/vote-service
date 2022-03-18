package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.java.demo.model.Dish;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {


    @Query("select d from Dish d where d.id = ?1 and d.menu.id = ?2")
    Optional<Dish> findByIdAndMenu(Long dishId, Long menuId);
}