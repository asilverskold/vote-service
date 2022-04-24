package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.model.Vote;
import ru.example.java.demo.repository.RestaurantRepository;
import ru.example.java.demo.repository.UserRepository;
import ru.example.java.demo.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant getResult(LocalDate date) {
        Long restaurantId = voteRepository.findVotesByDateEquals(date)
                .stream()
                .collect(Collectors.groupingBy(e -> e.getRestaurant().getId(), Collectors.counting()))
                .entrySet()
                .stream()
                .max((e1, e2) -> e1.getValue() > e2.getValue() ? 1 : -1)
                .orElseThrow(()-> new RuntimeException("No votes"))
                .getKey();

        return restaurantRepository.findById(restaurantId).orElseThrow();
    }

    @Transactional
    public void vote(Long restaurantId, Long userId) {
       // checkTheVotingTime();

        Vote vote = voteRepository.findByVote(LocalDate.now(), userId)
                .orElse(new Vote());
        if (vote.getId() != null) {
            if (vote.getRestaurant().getId().equals(restaurantId)) {
                voteRepository.delete(vote);
                return;
               // throw new RuntimeException("Already voted");
            }
        }

        vote.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(()-> new RuntimeException("No restaurant " + restaurantId)));
        vote.setUser(userRepository.getById(userId));
        voteRepository.save(vote);

    }

    private void checkTheVotingTime() {
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0));

        if (LocalDateTime.now().isAfter(of)) {
            throw new RuntimeException("Voting is over");
        }
    }


}
