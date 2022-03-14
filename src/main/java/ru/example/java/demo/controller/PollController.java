package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.dto.VoteRequest;
import ru.example.java.demo.model.Poll;
import ru.example.java.demo.model.PollOption;
import ru.example.java.demo.service.PollService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/polls")
public class PollController {

    private final PollService pollService;

    @PostMapping()
    public Poll create(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    @PutMapping()
    public Poll update(@RequestBody Poll poll) {
        return pollService.updatePoll(poll);
    }

    @DeleteMapping()
    public void delete(@RequestParam Long pollId) {
       pollService.deletePoll(pollId);
    }

    @GetMapping()
    public Collection<Poll> findAll() {
        return pollService.findAllPolls();
    }

    @GetMapping("/{id}")
    public Poll findById(@PathVariable Long id) {
        return pollService.findPollById(id);
    }


    @PostMapping("/{pollid}/options")
    public void addPollOption(@PathVariable Long pollid, @RequestBody PollOption option) {
        pollService.addOptionToPoll(pollid, option);
    }

    @PostMapping("/{pollid}/vote")
    public void addPollOption(@PathVariable Long pollid, @RequestBody VoteRequest voteRequest) {
        voteRequest.setPollid(pollid);
        pollService.vote(voteRequest);
    }
}
