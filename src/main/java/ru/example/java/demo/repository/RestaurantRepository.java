package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.example.java.demo.model.Restaurant;

import java.time.LocalDate;
import java.util.Collection;
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

@Query("select r, COUNT(v) as quantity from Restaurant r,Vote v where  v.restaurant = r and v.date =?1 group by r order by quantity DESC")
Collection<Object> findByR(LocalDate date);
}