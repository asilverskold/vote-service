package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.java.demo.model.Menu;

import java.time.LocalDate;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("select m from Menu m where m.restaurant.id = ?1 and m.date = ?2")
    Optional<Menu> findByRestaurantIdAndDate(Long restaurantId, LocalDate date);
}