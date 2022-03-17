package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.java.demo.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}