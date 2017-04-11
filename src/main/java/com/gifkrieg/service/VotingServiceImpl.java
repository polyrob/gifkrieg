package com.gifkrieg.service;

import com.gifkrieg.data.VotingRepository;
import com.gifkrieg.model.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by robbie on 4/10/17.
 */
@Service("votingService")
public class VotingServiceImpl implements VotingService {

    @Autowired
    private VotingRepository votingRepository;

    @Override
    public int getVotesForChallenge(Challenge voting) {
        return votingRepository.countByChallengeId(voting.getId());
    }
}
