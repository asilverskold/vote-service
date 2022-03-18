package ru.example.java.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.demo.dto.VoteRequest;
import ru.example.java.demo.model.poll.Poll;
import ru.example.java.demo.model.poll.PollOption;
import ru.example.java.demo.model.poll.Vote;
import ru.example.java.demo.repository.poll.PollOptionRepository;
import ru.example.java.demo.repository.poll.PollRepository;
import ru.example.java.demo.repository.UserRepository;
import ru.example.java.demo.repository.poll.VoteRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Transactional
    public void deletePoll(Long pollId) {
        pollRepository.deleteById(pollId);
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
    public void deleteOptionToPoll(Long pollOptionId, Long pollId ) {
        pollOptionRepository.deleteByIdAndPoll(pollOptionId, pollId);
    }

    @Transactional
    public void vote(VoteRequest voteRequest) {
        Long pollId = voteRequest.getPollid();
        Long optionId = voteRequest.getOptionId();
        Long userId = voteRequest.getUserId();

        validateVoting(pollId);

        Vote vote = voteRepository.findByVote(pollId, userId)
                .orElse(new Vote());

        if (vote.getId() != null) {

            if (vote.getPollOption()
                    .getId()
                    .equals(optionId)) {
                throw new RuntimeException("Already voted");
            }

            PollOption pollOption = pollOptionRepository.getById(vote.getPollOption()
                                                                         .getId());
            pollOption.setVotedCount(pollOption.getVotedCount() - 1);
            pollOptionRepository.save(pollOption);
        }


        PollOption pollOption = pollOptionRepository.getById(optionId);
        pollOption.setVotedCount(pollOption.getVotedCount() + 1);
        pollOptionRepository.save(pollOption);

        vote.setPoll(pollRepository.getById(pollId));
        vote.setPollOption(pollOption);
        vote.setUser(userRepository.getById(userId));
        voteRepository.save(vote);

    }

    private void validateVoting(Long pollId) {
        LocalDateTime of = LocalDateTime.of(pollRepository.getById(pollId).getDate(), LocalTime.of(11, 0));
        if (LocalDateTime.now().isAfter(of)) {
            throw new RuntimeException("Voting is over");
        }
    }


}
