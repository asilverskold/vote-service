package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
