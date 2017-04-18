package com.gifkrieg.service;

import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.data.SubmissionRepository;
import com.gifkrieg.data.VotingRepository;
import com.gifkrieg.exception.DuplicateRequestException;
import com.gifkrieg.exception.GifAlreadySubmittedException;
import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.State;
import com.gifkrieg.model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * Created by robbie on 4/9/17.
 */
@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private VotingRepository votingRepository;

    @Override
    public void submitSubmission(int challengeId, int gifId, int userId) throws DuplicateRequestException, GifAlreadySubmittedException {
        Submission submission = new Submission(challengeId, gifId, userId);
        if (submissionRepository.exists(Example.of(submission))) {
            throw new DuplicateRequestException("User has already submitted this.");
        }
        if (submissionRepository.existsByChallengeIdAndGifId(challengeId, gifId)) {
            throw new GifAlreadySubmittedException("Another user has already submitted this GIF to this Challenge");
        }
        submissionRepository.save(submission);
    }


    @Override
    public boolean hasAlreadySubmittedForCurrentRound(int userId) {
        Challenge currentChallenge = challengeRepository.findByState(State.CURRENT);

        if (submissionRepository.existsByChallengeIdAndUserId(currentChallenge.getId(), userId)) {
            return true;
        }
        return false;
    }


    @Override
    public int getSubmissionCountForChallenge(Challenge c) {
        return submissionRepository.countByChallengeId(c.getId());
    }


}
