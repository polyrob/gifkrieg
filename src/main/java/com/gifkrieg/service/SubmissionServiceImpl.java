package com.gifkrieg.service;

import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.data.SubmissionRepository;
import com.gifkrieg.data.VotingRepository;
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
    public void submitSubmission(int challengeId, int gifId, int userId) throws Exception {
        Submission submission = new Submission(challengeId, gifId, userId);
        if (submissionRepository.exists(Example.of(submission))) {
            throw new Exception("User has already submitted this.");
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

    public boolean hasAlreadyVotedForVotingRound(int userId) {
        Challenge currentChallenge = challengeRepository.findByState(State.VOTING);

        if (votingRepository.existsByChallengeIdAndUserId(currentChallenge.getId(), userId)) {
            return true;
        }
        return false;
    }

    @Override
    public int getSubmissionCountForChallenge(Challenge c) {
        return submissionRepository.countByChallengeId(c.getId());
    }


}
