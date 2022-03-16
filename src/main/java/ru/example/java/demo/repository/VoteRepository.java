package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.example.java.demo.model.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {


     @Query(nativeQuery = true, value = "select * from vote where poll_id = :pollId and user_id = :userId")
   // @Query("select v from Vote v where v.poll.id = :pollId and v.pollOption.id = :userId")
    Optional<Vote> findByVote(@Param("pollId") Long pollId, @Param("userId") Long userId);


}
