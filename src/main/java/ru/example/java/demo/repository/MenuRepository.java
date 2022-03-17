package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.demo.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}