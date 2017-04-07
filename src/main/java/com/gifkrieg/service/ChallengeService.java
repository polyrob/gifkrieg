package com.gifkrieg.service;

import com.gifkrieg.model.Challenge;

import java.util.List;

/**
 * Created by robbie on 4/6/17.
 */
public interface ChallengeService {

    public List<Challenge> getCurrentAndPastChallenges(int past);
}
