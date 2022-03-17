package ru.example.java.demo.repository.poll;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.demo.model.poll.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
