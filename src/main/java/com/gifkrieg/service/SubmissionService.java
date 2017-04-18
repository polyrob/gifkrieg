package com.gifkrieg.service;

import com.gifkrieg.model.Challenge;

/**
 * Created by robbie on 4/9/17.
 */
public interface SubmissionService {
    void submitSubmission(int challengeId, int gifId, int userId) throws Exception;

    boolean hasAlreadySubmittedForCurrentRound(int userId);
    boolean hasAlreadyVotedForVotingRound(int userId);

    int getSubmissionCountForChallenge(Challenge current);
}
