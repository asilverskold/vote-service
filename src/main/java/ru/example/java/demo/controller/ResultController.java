package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.service.VoteService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
public class ResultController {
    private final VoteService voteService;

    @GetMapping
    public Restaurant getResult() {

        return voteService.getResult(LocalDate.now());
    }


}
