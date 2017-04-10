package com.gifkrieg.service;

import com.gifkrieg.constants.Defaults;
import com.gifkrieg.data.SubmissionRepository;
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
}
