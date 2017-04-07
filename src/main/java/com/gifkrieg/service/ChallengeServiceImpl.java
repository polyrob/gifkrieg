package com.gifkrieg.service;

import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.State;
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
    public List<Challenge> getCurrentAndPastChallenges(int past) {
        Challenge currentChallenge = challengeRepository.findByState(State.CURRENT);
        int currentId = currentChallenge.getId();
        List<Integer> pastList = new ArrayList<>();
        for (int i = 1; i <= past; i++) {
            pastList.add(currentId-i);
        }
        List<Challenge> recentChallenges = challengeRepository.findByIdIn(pastList);
        recentChallenges.add(0, currentChallenge);
        return recentChallenges;
    }


}
