package ru.example.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.example.java.demo.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {


    @Query(nativeQuery = true, value = "select count(*) > 0 from vote where poll_id = :pollId and user_id = :userId and poll_option_id = :pollOptionId")
    Boolean alreadyVoted(@Param("pollId") Long poolId, @Param("userId") Long userId, @Param("pollOptionId") Long pollOptionId);

}
