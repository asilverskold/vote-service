package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.demo.model.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
