package com.gifkrieg.service;

import com.gifkrieg.constants.Defaults;
import com.gifkrieg.data.ChallengeRepository;
import com.gifkrieg.data.SubmissionRepository;
import com.gifkrieg.data.VotingRepository;
import com.gifkrieg.model.Challenge;
import com.gifkrieg.model.State;
import com.gifkrieg.model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Submission> getVotableEntriesForChallenge(int challengeId, int userId) {
        return submissionRepository.getRandomSubmissions(challengeId, userId, Defaults.ENTRIES_TO_VOTE);
    }

    @Override
    public boolean hasAlreadySubmittedForCurrentRound(int userId) {
        Challenge currentChallenge = challengeRepository.findByState(State.CURRENT);

//        Submission query = new Submission();
//        query.setChallengeId(currentChallenge.getSubmissionId());
//        query.setUserId(userId);
//        ExampleMatcher NAME_MATCHER = ExampleMatcher.matching().withMa
//                .withMatcher("challenge_id", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
//        Example<Submission> example = Example.<Submission>of(sdd, NAME_MATCHER);
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
