package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.dto.VoteRequest;
import ru.example.java.demo.model.Poll;
import ru.example.java.demo.model.PollOption;
import ru.example.java.demo.model.Vote;
import ru.example.java.demo.repository.PollOptionRepository;
import ru.example.java.demo.repository.PollRepository;
import ru.example.java.demo.repository.UserRepository;
import ru.example.java.demo.repository.VoteRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @Transactional
    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Transactional
    public Poll updatePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public Poll findPollById(Long pollId) {
        return pollRepository.findById(pollId)
                .orElse(null);
    }

    public Collection<Poll> findAllPolls() {
        return pollRepository.findAll();
    }

    @Transactional
    public void addOptionToPoll(Long pollId, PollOption option) {
        option.setPoll(pollRepository.getById(pollId));
        option.setVotedCount(0);
        pollOptionRepository.save(option);
    }

    @Transactional
    public void vote(VoteRequest voteRequest) {
        Long pollId = voteRequest.getPollid();
        Long optionId = voteRequest.getOptionId();
        Long userId = voteRequest.getUserId();

        if (voteRepository.alreadyVoted(pollId, userId, optionId)) {
            throw new RuntimeException("Already voted");
        }

        PollOption pollOption = pollOptionRepository.getById(optionId);
        pollOption.setVotedCount(pollOption.getVotedCount() + 1);
        pollOptionRepository.save(pollOption);

        Vote vote = new Vote();
        vote.setPoll(pollRepository.getById(pollId));
        vote.setPollOption(pollOption);
        vote.setUser(userRepository.getById(userId));
        voteRepository.save(vote);

    }
}
