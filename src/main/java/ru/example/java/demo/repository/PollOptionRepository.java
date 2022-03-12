package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.demo.model.PollOption;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {

}
