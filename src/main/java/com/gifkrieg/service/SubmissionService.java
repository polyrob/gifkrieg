package com.gifkrieg.service;

import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.Submission;

import java.util.List;

/**
 * Created by robbie on 4/9/17.
 */
public interface SubmissionService {
    void submitSubmission(int challengeId, int gifId, int userId) throws Exception;

    List<Submission> getVotableEntriesForChallenge(int challengeId, int userId);

    boolean hasAlreadySubmittedForCurrentRound(int userId);
    boolean hasAlreadyVotedForVotingRound(int userId);

    int getSubmissionCountForChallenge(Challenge current);
}
