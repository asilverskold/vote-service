package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.model.user.User;
import ru.example.java.demo.repository.UserRepository;
import ru.example.java.demo.service.VoteService;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class VoteController {
    private final VoteService voteService;
    private final UserRepository userRepository;

    @GetMapping("result")
    public Restaurant getResult() {

        return voteService.getResult(LocalDate.now());
    }
    @PostMapping("vote/")
    public void vote(@RequestParam Long restaurantId, @AuthenticationPrincipal UserDetails user) {


        System.out.println(user.getUsername());
        voteService.vote(restaurantId,userRepository.findByUsername(user.getUsername()).get().getId());
    }


}
