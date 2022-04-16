package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.service.VoteService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class VoteController {
    private final VoteService voteService;

    @GetMapping("result")
    public Restaurant getResult() {

        return voteService.getResult(LocalDate.now());
    }
    @PostMapping("vote/{id}")
    public void vote(@PathVariable Long restaurantId, @PathVariable Long userId) {
        voteService.vote(restaurantId,userId);
    }


}
