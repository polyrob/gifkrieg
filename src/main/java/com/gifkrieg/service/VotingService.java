package com.gifkrieg.service;

import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.Submission;

import java.util.List;

/**
 * Created by robbie on 4/10/17.
 */
public interface VotingService {
    boolean hasAlreadyVotedForVotingRound(int userId);
    int getVotesForChallenge(Challenge voting);
    List<Submission> getVotableEntriesForChallenge(int challengeId, int userId);

    void castVoteForSubmission(int challengeId, int gifId, int userId);

    boolean hasUserVotedInRound(int userId, int challengeId);
}
