package com.gifkrieg.service;

import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.State;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Challenge getCurrentChallenge() {
        return challengeRepository.findByState(State.CURRENT);
    }

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
