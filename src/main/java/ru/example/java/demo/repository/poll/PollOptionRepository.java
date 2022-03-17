package ru.example.java.demo.repository.poll;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.demo.model.poll.PollOption;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {

}
