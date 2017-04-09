package com.gifkrieg.service;

import com.gifkrieg.data.ChallengeRepository;
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

    @Cacheable(value = "currentChallenge")
    @Override
    public Challenge getCurrentChallenge() {
        return challengeRepository.findByState(State.CURRENT);
    }

    @Cacheable(value = "challengeHistory")
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
