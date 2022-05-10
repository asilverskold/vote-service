package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.model.Vote;
import ru.example.java.demo.repository.RestaurantRepository;
import ru.example.java.demo.repository.UserRepository;
import ru.example.java.demo.repository.VoteRepository;
import ru.example.java.demo.service.exception.MyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant getResult(LocalDate date) {
       /* Long restaurantId = voteRepository.findVotesByDateEquals(date)
                .stream()
                .peek(e-> System.out.println(e.getDate().toString()))
                .collect(Collectors.groupingBy(e -> e.getRestaurant().getId(), Collectors.counting()))
                .entrySet()
                .stream()
                .max((e1, e2) -> e1.getValue() > e2.getValue() ? 1 : -1)
                .orElseThrow(()-> new MyException("No votes"))
                .getKey();
        */
        return (Restaurant) restaurantRepository.findByR(date)
                .stream()
                .map(e -> (Object[]) e)
                .map(e -> new Object(){Restaurant r = (Restaurant)e[0];
                                        Long q =(Long) e[1];})
                .max((e, e1) -> e.q > e1.q ? 1 : -1)
                .orElseThrow(() -> new MyException("No votes"))
                .r;

        //restaurantRepository.findByR(date).stream().peek(e -> {e.keySet().stream().forEach(i-> System.out.println(i));}).findFirst().get().keySet().stream().findFirst().get(); //restaurantRepository.findById(restaurantId).orElseThrow();
    }

    @Transactional
    public void vote(Long restaurantId, Long userId) {
        checkTheVotingTime();

        Vote vote = voteRepository.findByVote(LocalDate.now(), userId)
                .orElse(new Vote());
        if (vote.getId() != null) {
            if (vote.getRestaurant().getId().equals(restaurantId)) {
                voteRepository.delete(vote);
                return;
                // throw new MyException("Already voted");
            }
        }

        vote.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() -> new MyException("No restaurant " + restaurantId, HttpStatus.BAD_REQUEST)));
        vote.setUser(userRepository.getById(userId));
        voteRepository.save(vote);

    }

    private void checkTheVotingTime() {
        LocalDateTime of = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0));

        if (LocalDateTime.now().isAfter(of)) {
            throw new MyException("Voting is over", HttpStatus.OK);
        }
    }


}
