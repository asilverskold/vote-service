package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.java.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

   @Query("select u from User u where u.username = ?1")
   Optional<User> findByUsername(String username);

   User save(User user);
}
