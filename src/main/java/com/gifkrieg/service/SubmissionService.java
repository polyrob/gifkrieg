package com.gifkrieg.service;

import com.gifkrieg.model.Submission;

import java.util.List;

/**
 * Created by robbie on 4/9/17.
 */
public interface SubmissionService {
    public void submitSubmission(int challengeId, int gifId, int userId) throws Exception;

    List<Submission> getVotableEntriesForChallenge(int challengeId, int userId);
}
