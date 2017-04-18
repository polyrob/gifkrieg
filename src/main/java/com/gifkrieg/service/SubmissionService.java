package com.gifkrieg.service;

import com.gifkrieg.exception.DuplicateRequestException;
import com.gifkrieg.exception.GifAlreadySubmittedException;
import com.gifkrieg.model.Challenge;

/**
 * Created by robbie on 4/9/17.
 */
public interface SubmissionService {
    void submitSubmission(int challengeId, int gifId, int userId) throws DuplicateRequestException, GifAlreadySubmittedException;

    boolean hasAlreadySubmittedForCurrentRound(int userId);

    int getSubmissionCountForChallenge(Challenge current);
}
