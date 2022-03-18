package ru.example.java.demo.repository.poll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.poll.PollOption;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {

    @Transactional
    @Modifying
    @Query("delete from PollOption p where p.id = ?1 and p.poll.id = ?2")
    void deleteByIdAndPoll(Long pollOptionId, Long pollId );
}
