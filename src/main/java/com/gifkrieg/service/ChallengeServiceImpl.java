package com.gifkrieg.service;

import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.data.SubmissionRepository;
import com.gifkrieg.data.VotingRepository;
import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robbie on 4/6/17.
 */
@Service("challengeService")
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private VotingRepository votingRepository;


    // TODO: get these challenges in single query
    @Override
    public Challenge getCurrentChallenge() {
        Challenge c = challengeRepository.findByState(State.CURRENT);
        c.setSubmissions(submissionRepository.countByChallengeId(c.getId()));
        return c;
    }

    @Override
    public Challenge getVotingChallenge() {
        Challenge c = challengeRepository.findByState(State.VOTING);
        c.setSubmissions(submissionRepository.countByChallengeId(c.getId()));
        c.setVotes(votingRepository.countByChallengeId(c.getId()));
        return c;
    }

    @Override
    public Challenge getCompletedChallenge() {
        return challengeRepository.findByState(State.COMPLETE);
    }


    //@Cacheable(value = "challengeHistory")
    @Override
    public List<Challenge> getChallenges(int fromId, int toId) {
        if (fromId > toId) throw new IllegalArgumentException("fromId must be <= toId");
        List<Integer> pastList = new ArrayList<>();
        for (int i = fromId; i <= toId; i++) {
            pastList.add(i);
        }
        List<Challenge> recentChallenges = challengeRepository.findByIdInOrderByIdDesc(pastList);
        return recentChallenges;
    }

}
