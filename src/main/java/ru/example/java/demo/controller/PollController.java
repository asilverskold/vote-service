package ru.example.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.java.demo.dto.VoteRequest;
import ru.example.java.demo.model.Restaurant;
import ru.example.java.demo.model.poll.Poll;
import ru.example.java.demo.model.poll.PollOption;
import ru.example.java.demo.service.PollService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PollController {

    private final PollService pollService;

    @PostMapping("/polls")
    public Poll create(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    @PutMapping("/polls")
    public Poll update(@RequestBody Poll poll) {
        return pollService.updatePoll(poll);
    }

    @DeleteMapping("/polls")
    public void delete(@RequestParam Long pollId) {
        pollService.deletePoll(pollId);
    }

    @GetMapping("/polls")
    public Collection<Poll> findAll() {
        return pollService.findAllPolls();
    }

    @GetMapping("/polls/{id}")
    public Poll findById(@PathVariable Long id) {
        return pollService.findPollById(id);
    }

    @PostMapping("/polls/{pollid}/options")
    public void addPollOption(@PathVariable Long pollid, @RequestBody PollOption option) {
        pollService.addOptionToPoll(pollid, option);
    }

    @DeleteMapping("/polls/{pollid}/options")
    public void addPollOption(@RequestParam Long option, @RequestParam Long pollid) {
        pollService.deleteOptionToPoll(option, pollid);
    }

    @PostMapping("/polls/{pollid}/vote")
    public void addPollOption(@PathVariable Long pollid, @Valid @RequestBody VoteRequest voteRequest) {
        voteRequest.setPollid(pollid);
        pollService.vote(voteRequest);
    }

}
