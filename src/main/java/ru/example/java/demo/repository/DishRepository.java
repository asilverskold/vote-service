package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.demo.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}